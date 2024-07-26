package uta.cse3310;

import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;
    private boolean iswinner;
    private GameTimer timer; // Updated reference

    

    public boolean isWinner() {
        return iswinner;
    }

    public void setWinner(boolean iswinner) {
        this.iswinner = iswinner;
    }

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = 0;
        this.timer = new GameTimer(); // Updated reference
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

    public void resetScore() {
        this.score = 0;
    }
}



