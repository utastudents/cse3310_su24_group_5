package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;

public class Round {
    private Word word;
    private Stake stake;
    private int currentPlayerIndex;
    private List<Player> players;
    private boolean isRoundActive;
    private static final int TURN_TIME_LIMIT = 100;
    private static final int VOWEL_COST = 50;
    private WordList wordlist = new WordList();
    private ArrayList<String> wordsforgame;
    HashSet<Character> lettersguessed = new HashSet<>();
    HashSet<Character> correctguesses = new HashSet<>();
    private HashSet<Character> lettersinword;
    private transient Scanner scanner;

    @SuppressWarnings("static-access")
    public Round(List<Player> players, String wordFilePath, String stakeFilePath, Scanner scanner) throws IOException {
        this.players = players;
        this.scanner = scanner;
        wordlist.gatherwords();
        this.wordsforgame = new ArrayList<>(wordlist.getArrList());
        System.out.println("words chosen" + wordsforgame);
        this.lettersinword = wordlist.findletters(wordsforgame);
        this.word = new Word(wordsforgame);
        this.stake = new Stake(stakeFilePath);
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    @SuppressWarnings("static-access")
    public Round(List<Player> players, WordList wordlist, String stakeFilePath, Scanner scanner) throws IOException {
        this.players = players;
        this.wordlist = wordlist;
        this.scanner = scanner;
        this.wordsforgame = new ArrayList<>(wordlist.getWords());
        System.out.println("words chosen" + wordsforgame);
        this.lettersinword = wordlist.findletters(wordsforgame);
        this.word = new Word(wordsforgame);
        this.stake = new Stake(stakeFilePath);
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    public void startRound() {
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

        //presentOptions(currentPlayer);

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
/* 
    @SuppressWarnings("static-access")
    private void presentOptions(Player currentPlayer) {
        word.getWordProgress(wordsforgame, correctguesses);
        System.out.println("\ncurrent word(s) to guess:" + wordsforgame);
        System.out.println("Choose an option:");
        System.out.println("1. Buy a vowel");
        System.out.println("2. Select a consonant");
        System.out.println("3. Solve the puzzle");
        int choice = getPlayerChoice();
        handleChoice(currentPlayer, choice);
    }

    private int getPlayerChoice() {
        int choice = scanner.nextInt();
        System.out.println("Player choice: " + choice);
        return choice;
    }

    private void handleChoice(Player currentPlayer, int choice) {
        switch (choice) {
            case 1:
                buyVowel(currentPlayer);
                break;
            case 2:
                selectConsonant(currentPlayer);
                break;
            case 3:
                solvePuzzle(currentPlayer);
                break;
            default:
                System.out.println("Invalid choice. Turn skipped.");
                advanceTurn();
                break;
        }
    }*/

    public void buyVowel(Player currentPlayer, char vowel) {
        /*boolean validVowel = false;
        while (!validVowel) {
            //System.out.println("Enter a vowel to buy:");
            //char vowel = getUserInput();
            if ("aeiou".indexOf(vowel) != -1 && currentPlayer.getScore() >= VOWEL_COST && !currentPlayer.hasBoughtVowel(vowel)) {
                validVowel = true;
                currentPlayer.deductScore(VOWEL_COST);
                currentPlayer.buyVowel(vowel);
                processGuess(currentPlayer, vowel);
            } else {
                System.out.println("Invalid vowel or not enough points or already bought. Try again.");
            }
        }*/

        //System.out.println("Attempting to buy vowel: " + vowel); // Debugging statement
        if ("aeiou".indexOf(vowel) != -1 && currentPlayer.getScore() >= VOWEL_COST && !currentPlayer.hasBoughtVowel(vowel)) {
            currentPlayer.deductScore(VOWEL_COST);
            currentPlayer.buyVowel(vowel);
            processGuess(currentPlayer, vowel);
        } else {
            System.out.println("Invalid vowel or not enough points or already bought. Try again.");
        }
    }

    public void selectConsonant(Player currentPlayer, char consonant) {
        /*boolean validConsonant = false;
        while (!validConsonant) {
            //System.out.println("Enter a consonant to select:");
            //char consonant = getUserInput();
            if ("aeiou".indexOf(consonant) == -1 && !currentPlayer.hasGuessedConsonant(consonant)) {
                validConsonant = true;
                currentPlayer.guessConsonant(consonant);
                processGuess(currentPlayer, consonant);
            } else {
                System.out.println("Invalid consonant or already guessed. Try again.");
            }
        }*/

        if ("aeiou".indexOf(consonant) == -1 && !currentPlayer.hasGuessedConsonant(consonant)) {
            currentPlayer.guessConsonant(consonant);
            processGuess(currentPlayer, consonant);
        } else {
            System.out.println("Invalid consonant or already guessed. Try again.");
        }
    }

    public void solvePuzzle(Player currentPlayer, String solution) {
        //System.out.println("Enter the solution:");
        //String solution = scanner.next();
        //System.out.println("User solution input: " + solution);
        String solutions = solution.replace(" ", "");
        if (word.solve(solutions)) {
            System.out.println(currentPlayer.getName() + " solved the puzzle!");
            currentPlayer.addScore(10);
            isRoundActive = false;
            System.out.println("going to next round");
        } else {
            System.out.println("Incorrect solution by player " + currentPlayer.getName());
            advanceTurn();
        }
    }

    private char getUserInput() {
        char input = scanner.next().charAt(0);
        System.out.println("User input: " + input);
        return input;
    }

    @SuppressWarnings("static-access")
    private void processGuess(Player currentPlayer, char guessedLetter) {
        int isCorrect = word.iscorrect(guessedLetter, lettersinword);
        if (isCorrect == 1) {
            System.out.println("Correct guess!");
            int points = stake.calculatePoints(guessedLetter);
            currentPlayer.addScore(points);
            correctguesses.add(guessedLetter);
            System.out.println("Player " + currentPlayer.getName() + " awarded " + points + " points.");
            System.out.println("\ncorrect guesses: " + correctguesses);
            if (correctguesses.equals(lettersinword)) {
                System.out.println("Word guessed correctly! Round over.");
                isRoundActive = false;
            } else {
                currentPlayer.getTimer().reset();
                nextTurn();
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

    public void resetRound() throws IOException {
        this.stake.reset();
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    public boolean isRoundActive() {
        return isRoundActive;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public ArrayList<String> getWordsForGame() {
        return wordsforgame;
    }

    /*public static void main(String[] args) throws IOException {
        List<String> testwords = Arrays.asList("testword");
        WordList wordlist = new WordList(testwords);

        List<Player> players = new ArrayList<>();
        // Simulated user input for two rounds
        String simulatedUserInput = "3\n" + "testword\n" + "3\n" + "testword\n" + "3\n" + "testword\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        //System.setIn(inputStream);
        Scanner scanner = new Scanner(System.in);

        Game game = new Game(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt", new Statistics(), scanner);

        game.addPlayer(new Player("Player1", PlayerType.HUMAN));
        game.addPlayer(new Player("Player2", PlayerType.HUMAN));
        game.startGame();
        System.exit(0);
    }*/
}
