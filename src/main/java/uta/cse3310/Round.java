package uta.cse3310;

import java.io.IOException;
import java.util.List;

public class Round {
    private Word word;
    private Stake stake;
    private int currentPlayerIndex;
    private List<Player> players;
    private boolean isRoundActive;

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
        System.out.println("New round started. Word to guess: " + word.getWordProgress());
    }

    public void nextTurn() {
        if (!isRoundActive) {
            System.out.println("Round is not active.");
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Current player: " + currentPlayer.getName());
        
        char guessedLetter = 'a'; // Replace with actual guess from player input
        boolean isCorrect = word.guessLetter(guessedLetter);
        if (isCorrect) {
            System.out.println("Correct guess!");
        } else {
            System.out.println("Incorrect guess.");
        }
        
        if (word.isFullyGuessed()) {
            System.out.println("Word guessed correctly! Round over.");
            isRoundActive = false;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public String getCurrentWordProgress() {
        return word.getWordProgress();
    }

   /*  public String getCurrentStake() {
        return stake.getCurrentStake();
    }*/

    public void resetRound() throws IOException {
        this.word.reset();
       // this.stake.reset();
        this.currentPlayerIndex = 0;
        this.isRoundActive = true;
    }

    public boolean isRoundActive() {
        return isRoundActive;
    }
}




