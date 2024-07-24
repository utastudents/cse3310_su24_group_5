import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private List<Round> rounds;
    private Round currentRound;
    private int currentPlayerIndex;
    private boolean isGameActive;

    public Game(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        rounds = new ArrayList<>();
        currentPlayerIndex = 0;
        isGameActive = false;
    }

    public void startGame() {
        System.out.println("Game started!");
        isGameActive = true;
        startNewRound();
    }

    private void startNewRound() {
        currentRound = new Round(players);
        rounds.add(currentRound);
        currentRound.startRound();
    }

    public void endRound() {
        System.out.println("Round ended!");
        if (rounds.size() < 3) {
            startNewRound();
        } else {
            endGame();
        }
    }

    public void endGame() {
        System.out.println("Game ended!");
        int winnerIndex = calculateWinner();
        System.out.println("The winner is " + players[winnerIndex].getName());
        isGameActive = false;
    }

    public int calculateWinner() {
        int maxScore = -1;
        int winnerIndex = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getScore() > maxScore) {
                maxScore = players[i].getScore();
                winnerIndex = i;
            }
        }
        return winnerIndex;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    // Added method to handle stakes
    public void applyStake(int stakeIndex) {
        Stake selectedStake = currentRound.getStakes().get(stakeIndex); // Added line
        int stakeValue = selectedStake.applyStake(); // Added line
        System.out.println("Applied stake of value: " + stakeValue); // Added line
    }
}

