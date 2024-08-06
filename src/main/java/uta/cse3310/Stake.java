package uta.cse3310;

public class Stake {
    private String currentStake;

    // Default constructor
    public Stake() {
    }

    // Constructor that accepts a string argument
    public Stake(String currentStake) {
        this.currentStake = currentStake;
    }

    public String getCurrentStake() {
        return currentStake;
    }

    public void setCurrentStake(String currentStake) {
        this.currentStake = currentStake;
    }

    public void reset() {
        this.currentStake = null;  // Or any default value
    }

    public int calculatePoints(char guessedLetter) {
        // Placeholder for actual point calculation logic
        // Implement your game logic here
        return 10; // Example fixed value, replace with actual logic
    }
}





