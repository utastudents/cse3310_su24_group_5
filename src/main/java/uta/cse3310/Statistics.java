package uta.cse3310;

public class Statistics {
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private Player winner;

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesLost() {
        gamesLost++;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void updateWinner(Player player) {
        if (player.isWinner()) {
            incrementGamesWon();
            this.winner = player; // Update the winner
        } else {
            incrementGamesLost();
        }
    }

    public Player getWinner() {
        return winner;
    }
}




