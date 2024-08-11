package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stake {
    private List<String> stakes;
    private String currentStake; //<---------added

    public Stake(String filePath) throws IOException {
        this.stakes = new ArrayList<>();
        loadStakes(filePath);
    }

    public List<String> getStakes()
    {
        return stakes;
    }

    private void loadStakes(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                stakes.add(line.trim());
            }
        }
    }

    public String getRandomStake() {
        Random random = new Random();
        return stakes.get(random.nextInt(stakes.size()));
    }

    public void resetcurrentStake() {
        this.currentStake = null;  // Or any default value
    }

    public void reset() {
        this.stakes = null;  // Or any default value
    }

    public int calculatePoints(String stake, char guessedLetter) {
        if (stake == null) {
            System.err.println("Stake is null. Defaulting to 0 points.");
            return 0;
        }

        switch (stake) {
            case "Bankrupt":
                return 0;
            case "Lose Turn":
                return 0;
            case "Double Points":
                return 2 * 10; // Double points, assuming base is 10
            case "Half Points":
                return 5; // Half points, assuming base is 10
            case "Free Spin":
            case "Extra Turn":
                return 10; // Regular points
            default:
                try {
                    return Integer.parseInt(stake);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid stake format: " + stake);
                    return 0;
                }
        } 
    }


    public void setCurrentStake(String currentStake) { //<---------added
        this.currentStake = currentStake;
    }

    public String getCurrentStake() { //<---------added
        return currentStake;
    }
}









