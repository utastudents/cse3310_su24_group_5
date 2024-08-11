package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.java_websocket.WebSocket;

import com.google.gson.Gson;

public class Game {
    private static final int MAX_PLAYERS = 4;
    private List<Player> players;
    List<Round> rounds;
    private int currentRoundIndex;
    public boolean isGameActive;
    private Statistics stats;
    private int gameId;
    private boolean inTestingMode;
    public boolean playerActionComplete;
    //private transient Scanner scanner;  // Marked as transient
    boolean correctGuess;
    //private TurnStartListener turnStartListener;

    public Game(List<Player> players, String wordFilePath, String stakeFilePath, Statistics stats) throws IOException {
        this.players = players;
        this.rounds = new ArrayList<>();
        this.currentRoundIndex = 0;
        this.isGameActive = false;
        this.stats = stats;  // Keep the passed stats
        this.gameId = 0;
        this.inTestingMode = false;
        this.playerActionComplete = false;
    
        for (int i = 0; i < 3; i++) {
            rounds.add(new Round(players, wordFilePath, stakeFilePath));
        }
    }

    public Game(List<Player> players, List<Round> rounds, int currentRoundIndex, boolean isGameActive, int gameId, boolean playerActionComplete, boolean correctGuess) {
        this.players = players;
        this.rounds = rounds;
        this.currentRoundIndex = currentRoundIndex;
        this.isGameActive = isGameActive;
        this.gameId = gameId;
        this.playerActionComplete = playerActionComplete;
        this.correctGuess = correctGuess;
        // Initialize transient fields or any other required fields as needed
        //this.scanner = new Scanner(System.in);  // or set it to null if it should be handled differently
    }

    public void setTestingMode(boolean inTestingMode) {
        this.inTestingMode = inTestingMode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public boolean addPlayer(Player player) {
        if (players.size() < MAX_PLAYERS) {
            players.add(player);
            return true;
        } else {
            System.out.println("Cannot add player: maximum number of players reached.");
            return false;
        }
    }

    public void removePlayer(WebSocket conn) {
        players.removeIf(player -> player.getId().equals(conn.getAttachment()));
    }

    public void update(UserEvent event) {
        if (event == null) {
            System.err.println("Received null event");
            return;
        }
    
        String action = event.getAction();
        if (action == null) {
            System.err.println("Event action is null");
            return;
        }
    
        Round currentRound = rounds.get(currentRoundIndex);
        Player currentPlayer = currentRound.getCurrentPlayer();
        
    
        switch (action) {
            case "BUY_VOWEL":
                correctGuess = currentRound.buyVowel(currentPlayer, event.getValue().charAt(0));
                break;
            case "SELECT_CONSONANT":
                correctGuess = currentRound.selectConsonant(currentPlayer, event.getValue().charAt(0));
                System.out.println("Consonant selected by " + currentPlayer.getName());
                break;
            case "SOLVE_PUZZLE":
                correctGuess = currentRound.solvePuzzle(currentPlayer, event.getValue());
                break;
            default:
                System.out.println("Unknown action: " + action);
        }

        System.out.println("Updated game state: " + new Gson().toJson(this));
    
        if (playerActionComplete) {
            System.out.println("Player " + currentPlayer.getName() + " action complete.");
        }
    }
    

    
    

    public void startGame() {
        if (players.size() < 2) {
            System.out.println("Cannot start game: not enough players.");
            return;
        }
    
        if (isGameActive) {
            System.out.println("Game is already active.");
            return;
        }
    
        System.out.println("Game started with " + players.size() + " players.");
        isGameActive = true;

        startNextRound();
    }

    public void moveToNextRoundOrEndGame() {
        if (currentRoundIndex < rounds.size() - 1) {
            System.out.println("Round is over. Moving to the next round.");
            currentRoundIndex++; // Move to the next round
            startNextRound(); // Start the next round
        } else {
            System.out.println("All rounds complete. Ending the game.");
            endGame(determineWinner()); // End the game and declare the winner
        }
    }

    public void startNextRound(){
        Round currentRound = rounds.get(currentRoundIndex);
        currentRound.startRound();
    }

    public void playRound() {
        if (!isGameActive) {
            System.out.println("Game is not active.");
            return;
        }

        Round currentRound = rounds.get(currentRoundIndex);
        while (currentRound.isRoundActive()) {
            currentRound.nextTurn();
        }
        startNextRound();

        if (!currentRound.isRoundActive()) {
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

    public void endGame() {
        stats.incrementGamesPlayed();
        //stats.updateWinner(player);
    }

    public Statistics getStatistics() {
        return stats;
    }

    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public Round getCurrentRound() {
        return rounds.get(currentRoundIndex);
    }

    public Player determineWinner() {
        Player winner = null;
        int highestScore = 0;
        for (Player player : players) {
            if (player.getScore() > highestScore) {
                highestScore = player.getScore();
                winner = player;
            }
        }

        if (winner != null) {
            winner.setWinner(true);
            System.out.println("The winner is " + winner.getName() + " with a score of " + highestScore);
            stats.updateWinner(winner);
        } else {
            System.out.println("No winner determined.");
        }

        return winner;
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

    public boolean hasPlayer(Player player) {
        return players.stream().anyMatch(p -> p.getId().equals(player.getId()));
    }
}