package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
    public boolean waitingForInput = false;
    private final Object turnLock = new Object();

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
        System.out.println("Returning after nextTurn");
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
        waitingForInput = true;

        synchronized (turnLock) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    synchronized (turnLock) {
                        if (currentPlayer.getTimer().getElapsedTime() >= TURN_TIME_LIMIT || !waitingForInput) {
                            System.out.println("Time is up for player " + currentPlayer.getName());
                            currentPlayer.getTimer().stop();
                            waitingForInput = false;
                            turnLock.notifyAll();
                            System.out.println("Reached inside loop");
                            advanceTurn();
                        }
                    }
                }
            }, TURN_TIME_LIMIT * 1000);

            while (waitingForInput) {
                try {
                    turnLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        }
    }

    public void playerActionTaken() {
        synchronized (turnLock) {
            waitingForInput = false;
            turnLock.notifyAll();
        }
    }

    public void buyVowel(Player currentPlayer, char vowel) {
        if ("aeiou".indexOf(vowel) != -1 && currentPlayer.getScore() >= VOWEL_COST && !currentPlayer.hasBoughtVowel(vowel)) {
            currentPlayer.deductScore(VOWEL_COST);
            currentPlayer.buyVowel(vowel);
            processGuess(currentPlayer, vowel);
            playerActionTaken(); // Notify the waiting thread
        } else {
            System.out.println("Invalid vowel or not enough points or already bought. Try again.");
        }
    }

    public void selectConsonant(Player currentPlayer, char consonant) {
        if ("aeiou".indexOf(consonant) == -1 && !currentPlayer.hasGuessedConsonant(consonant)) {
            currentPlayer.guessConsonant(consonant);
            processGuess(currentPlayer, consonant);
            playerActionTaken(); // Notify the waiting thread
        } else {
            System.out.println("Invalid consonant or already guessed. Try again.");
        }
    }

    public void solvePuzzle(Player currentPlayer, String solution) {
        String solutions = solution.replace(" ", "");
        if (word.solve(solutions)) {
            System.out.println(currentPlayer.getName() + " solved the puzzle!");
            currentPlayer.addScore(10);
            correctguesses.addAll(lettersinword); // Ensure all letters are marked as correct
            isRoundActive = false;
            playerActionTaken(); // Notify the waiting thread
            System.out.println("Going to next round");
        } else {
            System.out.println("Incorrect solution by player " + currentPlayer.getName());
            advanceTurn();
        }
    }

    @SuppressWarnings("static-access")
    private void processGuess(Player currentPlayer, char guessedLetter) {
        int isCorrect = word.iscorrect(guessedLetter, lettersinword);
        lettersguessed.add(guessedLetter); // Add guessed letter to the list of guessed letters
        if (isCorrect == 1) {
            System.out.println("Correct guess!");
            int points = stake.calculatePoints(guessedLetter);
            currentPlayer.addScore(points);
            correctguesses.add(guessedLetter);
            System.out.println("Player " + currentPlayer.getName() + " awarded " + points + " points.");
            System.out.println("\nCorrect guesses: " + correctguesses);
            if (correctguesses.equals(lettersinword)) {
                System.out.println("Word guessed correctly! Round over.");
                isRoundActive = false;
                playerActionTaken(); // Notify the waiting thread
            } else {
                currentPlayer.getTimer().reset();
                //nextTurn();
            }
        } else {
            System.out.println("Incorrect guess.");
            advanceTurn();
        }
    }

    public void advanceTurn() {
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

    public HashSet<Character> getCorrectGuesses() {
        return correctguesses;
    }

    public HashSet<Character> getLettersGuessed() {
        return lettersguessed;
    }
}
