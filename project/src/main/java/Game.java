import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players = new Player[4];
    private List<Round> rounds = new ArrayList<>();
    private Round currentRound;

    public void startGame() {
        currentRound = new Round();
        currentRound.startRound();
        rounds.add(currentRound);
        // Initialize players and other game-specific variables
        System.out.println("Game started.");
    }

    public void endGame() {
        if (currentRound != null) {
            currentRound.endRound();
        }
        System.out.println("Game ended.");
        // Additional logic to finalize the game
        // This could include saving game state, calculating final scores, etc.
        calculateWinner();
    }

    public int calculateWinner() {
        // Implementation to calculate the winner
        // Placeholder logic for winner calculation
        // Assuming the player with the highest score wins
        int winnerIndex = -1;
        int highestScore = -1;

        for (int i = 0; i < players.length; i++) {
            if (players[i] != null && players[i].getScore() > highestScore) {
                highestScore = players[i].getScore();
                winnerIndex = i;
            }
        }

        System.out.println("Winner is player at index: " + winnerIndex);
        return winnerIndex;
    }

    // Additional methods for testing and game management
    public void addPlayer(Player player, int index) {
        if (index >= 0 && index < players.length) {
            players[index] = player;
        }
    }

    public Player getPlayer(int index) {
        if (index >= 0 && index < players.length) {
            return players[index];
        }
        return null;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}

