package uta.cse3310;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;
    private boolean iswinner;
    private GameTimer timer; // Updated reference
    private List<Character> boughtVowels;
    private List<Character> guessedConstants;

    
    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = 1000;
        this.timer = new GameTimer(); // Updated reference
        this.boughtVowels = new ArrayList<>();
        this.guessedConstants = new ArrayList<>();
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

    public void guessConstant(char constant) {
        this.guessedConstants.add(constant);
    }

    public List<Character> getGuessedConstants() {
        return this.guessedConstants;
    }

    public boolean hasGuessedConstant(char constant) {
        return this.guessedConstants.contains(constant);
    }
}

