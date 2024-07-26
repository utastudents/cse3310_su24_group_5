package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.WebSocket;

public class Game {

    private List<Player> players;
    private List<Round> rounds;
    private int currentRoundIndex;
    private boolean isGameActive;
    private Statistics stats;
    private int gameId;

    public Game(List<Player> players, String wordFilePath, String stakeFilePath, Statistics stats) throws IOException {
        this.players = players;
        this.rounds = new ArrayList<>();
        this.currentRoundIndex = 0;
        this.isGameActive = true;
        this.stats = stats;
        this.gameId = 0;
        stats = new Statistics();

        // Initialize rounds
        for (int i = 0; i < 3; i++) { // Assuming a game consists of 3 rounds
            rounds.add(new Round(players, wordFilePath, stakeFilePath));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void removePlayer(WebSocket conn) {
        players.removeIf(player -> player.getId().equals(conn.getAttachment()));
    }

    public void update(UserEvent event) {
        switch (event.getAction()) {
            case "PLAY":
                playRound();
                break;
            // Add cases for other actions as needed
            default:
                System.out.println("Unknown action: " + event.getAction());
        }
    }

    public void startGame() {
        System.out.println("Game started with " + players.size() + " players.");
        startNextRound();
    }

    public void startNextRound() {
        if (currentRoundIndex >= rounds.size()) {
            System.out.println("All rounds completed.");
            isGameActive = false;
            determineWinner();
            return;
        }

        Round currentRound = rounds.get(currentRoundIndex);
        currentRound.startRound();
        currentRoundIndex++;
    }

    public void playRound() {
        if (!isGameActive) {
            System.out.println("Game is not active.");
            return;
        }
    
        Round currentRound = rounds.get(currentRoundIndex - 1); // -1 because we incremented in startNextRound
        while (currentRound.isRoundActive()) {
            currentRound.nextTurn();
        }
    
        if (!currentRound.isRoundActive()) {
            startNextRound();
            if (currentRoundIndex >= rounds.size()) {
                System.out.println("All rounds completed.");
                isGameActive = false;
                determineWinner();
            }
        }
    }

    public void endGame(Player player) {
        stats.incrementGamesPlayed();
        stats.updateWinner(player);
    }

    public Statistics getStatistics() {
        return stats;
    }
    private void determineWinner() {
        Player winner = null;
        int highestScore = 0;
        for (Player player : players) {
            if (player.getScore() > highestScore) {
                highestScore = player.getScore();
                winner = player;
            }
        }
    
        if (winner != null) {
            winner.setWinner(true);  // Ensure that the winner is marked correctly
            System.out.println("The winner is " + winner.getName() + " with a score of " + highestScore);
            stats.updateWinner(winner);
        } else {
            System.out.println("No winner determined.");
        }
    }

    public void resetGame() {
        currentRoundIndex = 0;
        isGameActive = true;
        for (Round round : rounds) {
            try {
                round.resetRound();
            } catch (IOException e) {
                System.err.println("Error resetting round: " + e.getMessage());
            }
        }
    }

    public boolean isGameActive() {
        return isGameActive;
    }
}






