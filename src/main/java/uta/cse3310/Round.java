package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Round {
    private Word word;
    private Stake stake;
    private int currentPlayerIndex;
    private List<Player> players;
    private boolean isRoundActive;
    private static final int TURN_TIME_LIMIT = 1000000;
    private static final int VOWEL_COST = 50;
    private WordList wordlist = new WordList();
    private ArrayList<String> wordsforgame;
    HashSet<Character> lettersguessed = new HashSet<>();
    HashSet<Character> correctguesses = new HashSet<>();
    private HashSet<Character> lettersinword;
    public boolean waitingForInput = false;
    private Player currentPlayer;
    private String currentStake;

    @SuppressWarnings("static-access")
    public Round(List<Player> players, String wordFilePath, String stakeFilePath) throws IOException {
        this.players = players;
        wordlist.gatherwords();
        this.wordsforgame = new ArrayList<>(wordlist.getArrList());
        System.out.println("words chosen" + wordsforgame);
        this.lettersinword = wordlist.findletters(wordsforgame);
        this.word = new Word(wordsforgame);
        this.stake = new Stake(stakeFilePath);
        this.currentPlayerIndex = 0;
        this.isRoundActive = false;
    }

    /*@SuppressWarnings("static-access")
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
        this.isRoundActive = false;
    }*/

    public void startRound() {
        isRoundActive = true;
        //while (isRoundActive) {
            nextTurn();
            //System.out.println("finished nextTurn method");
        //}
        //System.out.println("Round is over");
    }

    public void nextTurn() {
        if (!isRoundActive) {
            System.out.println("Round is not active.");
            return;
        }
    
        currentPlayer = getCurrentPlayer();
        System.out.println("Current player: " + currentPlayer.getName() + " has " + TURN_TIME_LIMIT + " seconds to guess.");

        // Set the current stake for this player's turn
        currentStake = stake.getRandomStake(); // Initialize currentStake with a valid value

        // Start the player's timer
        currentPlayer.getTimer().reset();
        currentPlayer.getTimer().start();
        // The game flow will now wait for input, which will be handled in the onMessage method
    }

    public boolean buyVowel(Player player, char vowel) {
        boolean isCorrect;
        if ("aeiou".indexOf(vowel) != -1 && player.getScore() >= VOWEL_COST && !player.hasBoughtVowel(vowel)) {
            player.deductScore(VOWEL_COST);
            player.buyVowel(vowel);
            isCorrect = processGuess(player, vowel);
            return isCorrect;
        } else {
            System.out.println("Invalid vowel or not enough points or already bought. Try again.");
            return false;
        }
    }

    public boolean selectConsonant(Player player, char consonant) {
        boolean isCorrect;
        if ("aeiou".indexOf(consonant) == -1 && !player.hasGuessedConsonant(consonant)) {
            player.guessConsonant(consonant);
            isCorrect = processGuess(player, consonant);
            System.out.println("guess processed");
            return isCorrect;
        } else {
            System.out.println("Invalid consonant or already guessed. Try again.");
            return false;
        }
    }

    public boolean solvePuzzle(Player player, String solution) {
        String string = solution.replace(" ", "");

        if (word.solve(string)) {
            System.out.println(player.getName() + " solved the puzzle!");
            player.addScore(10);
            correctguesses.addAll(lettersinword); // Ensure all letters are marked as correct
            isRoundActive = false;
            System.out.println("Moving to next round.");
            return true;
        } else {
            System.out.println("Incorrect solution by player " + player.getName());
            player.getTimer().stop();
            return false;
        }
    }

    @SuppressWarnings("static-access")
    private boolean processGuess(Player player, char guessedLetter) {
        int isCorrect = word.iscorrect(guessedLetter, lettersinword);
        lettersguessed.add(guessedLetter);

        // if the guess is correct add points to player, add guessed letter, current player doesnt change (they get another turn)
        if (isCorrect == 1) {
            int reward = calculateReward(guessedLetter);
            player.addScore(reward);
            correctguesses.add(guessedLetter);
            
            //puzzle is solved
            if (correctguesses.equals(lettersinword)) {
                System.out.println("Word guessed correctly! Round over.");
                player.getTimer().stop(); //stop timer
                isRoundActive = false;
                return true;
            } 

            //reset the player's timer and allow them to guess again
            player.getTimer().reset();
            player.getTimer().start();
            System.out.println("Player " + player.getName() + " continues with a new timer.");
            return true;

        } else { //incorrect guess
            System.out.println("Incorrect guess.");
            player.getTimer().stop();
            return false;
        }
    }

    public void advanceTurn() {
        //Stop the timer for the current player
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.getTimer().stop();

        //Move to next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        
        //start the next player's turn
        nextTurn();
        
    }

    private int calculateReward(char guessedLetter) {
        int multiplier = stake.calculatePoints(currentStake, guessedLetter);
        return multiplier * (int) wordsforgame.stream().filter(word -> word.contains(String.valueOf(guessedLetter))).count();
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

    public String getCurrentStake() {
        return currentStake;
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