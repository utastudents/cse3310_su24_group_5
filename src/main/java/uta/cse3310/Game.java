package uta.cse3310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.WebSocket;


public class Game {
    private static final int MAX_PLAYERS = 4;
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
        this.stats = stats;  // Keep the passed stats
        this.gameId = 0;
    
        for (int i = 0; i < 3; i++) {
            rounds.add(new Round(players, wordFilePath, stakeFilePath));//error with this function
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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
    
        switch (action) {
            case "PLAY":
                playRound();
                break;
            default:
                System.out.println("Unknown action: " + action);
        }
    }

    public void startGame() {
        if (players.size() < 2) {
            System.out.println("Cannot start game: not enough players.");
            isGameActive = false;
            return;
        }
        System.out.println("Game started with " + players.size() + " players.");
        isGameActive = true;
        for(int i =0; i < 3; i++)
        {
           startNextRound(); 
            if(i ==2)
            {
            isGameActive = false;
            determineWinner();
            System.out.println("All round complete");

        }
    }
        //startNextRound();
    }

    public void startNextRound() {
        //if (currentRoundIndex >= rounds.size()) {
            //System.out.println("All rounds completed.");
            //isGameActive = false;
            //determineWinner();
          //  return;
        //}

        Round currentRound = rounds.get(currentRoundIndex);
        currentRound.startRound();
        currentRoundIndex++;
    }

    public void playRound() {
        if (!isGameActive) {
            System.out.println("Game is not active.");
            return;
        }

        Round currentRound = rounds.get(currentRoundIndex - 1);
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

    public Statistics getStatistics() {
        return stats;
    }

    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public void determineWinner() {
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








