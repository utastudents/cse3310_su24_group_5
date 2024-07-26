package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Round {
    private Word word;
    private Stake stake;
    private int currentPlayerIndex;
    private List<Player> players;
    private boolean isRoundActive;
    private static final int TURN_TIME_LIMIT = 100; // Time limit for each player's turn in seconds
    private static final int VOWEL_COST = 50; // Cost for buying a vowel

    public Round(List<Player> players, String wordFilePath, String stakeFilePath) throws IOException {
        this.players = players;
        this.word = new Word(loadWords(wordFilePath));
        this.stake = new Stake(stakeFilePath);
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    private String[] loadWords(String filePath) throws IOException {
        List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
        return lines.toArray(new String[0]);
    }

    public void startRound() {
        //System.out.println("New round started. Word to guess: " + word.getWordProgress());
        this.isRoundActive = true;
        nextTurn();
    }

    public void nextTurn() {
        if (!isRoundActive) {
            System.out.println("Round is not active.");
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.getTimer().reset();
        currentPlayer.getTimer().start();
        System.out.println("Current player: " + currentPlayer.getName() + " has " + TURN_TIME_LIMIT + " seconds to guess.");

        // Present options to the player
        presentOptions(currentPlayer);

        // Schedule a task to check the timer and move to the next turn if time is up
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                if (currentPlayer.getTimer().getElapsedTime() >= TURN_TIME_LIMIT) {
                    System.out.println("Time is up for player " + currentPlayer.getName());
                    currentPlayer.getTimer().stop();
                    advanceTurn();
                }
            }
        }, TURN_TIME_LIMIT * 1000);
    }

    private void presentOptions(Player currentPlayer) {
        System.out.println("Word progress: " + word.getWordProgress());
        System.out.println("Choose an option:");
        System.out.println("1. Buy a vowel");
        System.out.println("2. Select a constant");
        System.out.println("3. Solve the puzzle");

        // Get player input
        int choice = getPlayerChoice();
        handleChoice(currentPlayer, choice);
        
    }

    private int getPlayerChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void handleChoice(Player currentPlayer, int choice) {
        switch (choice) {
            case 1:
                boolean validVowel = false;
                while (!validVowel) {
                    System.out.println("Enter a vowel to buy:");
                    char vowel = getUserInput();
                    validVowel = buyVowel(currentPlayer, vowel);
                }
                break;
            case 2:
                boolean validConstant = false;
                while (!validConstant) {
                    System.out.println("Enter a constant to select:");
                    char constant = getUserInput();
                    validConstant = selectConstant(currentPlayer, constant);
                }
                break;
            case 3:
                System.out.println("Enter the solution:");
                Scanner scanner = new Scanner(System.in);
                String solution = scanner.nextLine();
                if (solvePuzzle(currentPlayer, solution)) {
                    isRoundActive = false;
                } else {
                    advanceTurn();
                }
                break;
            default:
                System.out.println("Invalid choice. Turn skipped.");
                advanceTurn();
                break;
        }
    }

    private char getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    private void processGuess(Player currentPlayer, char guessedLetter) {
        boolean isCorrect = word.guessLetter(guessedLetter);
        if (isCorrect) {
            System.out.println("Correct guess!");
            int points = stake.calculatePoints(guessedLetter);
            currentPlayer.addScore(points);
            System.out.println("Player " + currentPlayer.getName() + " awarded " + points + " points.");
            if (word.isFullyGuessed()) {
                System.out.println("Word guessed correctly! Round over.");
                isRoundActive = false;
            } else {
                currentPlayer.getTimer().reset();
                nextTurn(); // Player gets another turn
            }
        } else {
            System.out.println("Incorrect guess.");
            advanceTurn();
        }
    }

    private void advanceTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.getTimer().stop();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        if (isRoundActive) {
            nextTurn();
        }
    }

    public boolean buyVowel(Player player, char vowel) {
        if ("aeiou".indexOf(vowel) == -1) {
            System.out.println("Invalid vowel: " + vowel);
            return false;
        }

        if (player.getScore() < VOWEL_COST) {
            System.out.println("Player " + player.getName() + " does not have enough points to buy a vowel.");
            return false;
        }

        if (player.hasBoughtVowel(vowel)) {
            System.out.println("Vowel " + vowel + " has already been bought by player " + player.getName());
            return false;
        }

        player.deductScore(VOWEL_COST);
        player.buyVowel(vowel);

        processGuess(player, vowel);

        return true;
    }

    public boolean selectConstant(Player player, char constant) {
        if ("aeiou".indexOf(constant) != -1) {
            System.out.println("Invalid constant: " + constant);
            return false;
        }

        if (player.hasGuessedConstant(constant)) {
            System.out.println("Constant " + constant + " has already been selected by player " + player.getName());
            return false;
        }

        player.guessConstant(constant);

        processGuess(player, constant);

        return true;
    }

    public boolean solvePuzzle(Player player, String solution) {
        if (word.solve(solution)) {
            System.out.println(player.getName() + " solved the puzzle!");
            player.addScore(10); // Award points based on solution length
            isRoundActive = false;
            return true;
        } else {
            System.out.println("Incorrect solution by player " + player.getName());
            advanceTurn();
            return false;
        }
    }

    public String getCurrentWordProgress() {
        return word.getWordProgress();
    }

    public void resetRound() throws IOException {
        this.word.reset();
        this.stake.reset();
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    public boolean isRoundActive() {
        return isRoundActive;
    }

    public static void main(String[] args) throws IOException {
        List<Player> players = new ArrayList<>();
        Game game = new Game(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt", new Statistics());

        game.addPlayer(new Player("Player1"));
        game.addPlayer(new Player("Player2"));
        game.startGame();
    }
}
