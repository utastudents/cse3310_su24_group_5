<<<<<<< HEAD:src/main/java/uta/cse 3310/Game.java
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
        switch (event.type) {
            case "PLAY":
                playRound();
                break;
            // Add cases for other event types as needed
            default:
                System.out.println("Unknown event type: " + event.type);
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

        // After the round ends, start the next round
        startNextRound();
    }
    public void endGame(Player player) {
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
}




=======
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
        // Implementation of update method
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

        // After the round ends, start the next round
        startNextRound();
    }
    public void endGame(Player player) {
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
}




>>>>>>> 5a3285b74db3684848a43becdb6c3bf5a1d192fd:src/main/java/uta/cse3310/Game.java
