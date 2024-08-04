package uta.cse3310;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;
    private boolean iswinner;
    private GameTimer timer;
    private List<Character> boughtVowels;
    private List<Character> guessedConsonants;
    private PlayerType playerType;

    public Player(String name, PlayerType playerType) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = 1000;
        this.timer = new GameTimer();
        this.boughtVowels = new ArrayList<>();
        this.guessedConsonants = new ArrayList<>();
        this.playerType = playerType;
    }

    public boolean isWinner() {
        return iswinner;
    }

    public void setWinner(boolean iswinner) {
        this.iswinner = iswinner;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void deductScore(int points) {
        this.score -= points;
    }

    public void resetScore() {
        this.score = 0;
    }

    public GameTimer getTimer() {
        return timer;
    }

    public boolean hasBoughtVowel(char vowel) {
        return this.boughtVowels.contains(vowel);
    }

    public void buyVowel(char vowel) {
        this.boughtVowels.add(vowel);
    }

    public List<Character> getBoughtVowels() {
        return this.boughtVowels;
    }

    public void guessConsonant(char consonant) {
        this.guessedConsonants.add(consonant);
    }

    public List<Character> getGuessedConsonants() {
        return this.guessedConsonants;
    }

    public boolean hasGuessedConsonant(char consonant) {
        return this.guessedConsonants.contains(consonant);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}






