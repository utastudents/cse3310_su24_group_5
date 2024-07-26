package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private List<Player> players;
    private Statistics stats;
    private static final String TEST_WORD_FILE_PATH = "src/test/resources/test_words.txt";
    private static final String TEST_STAKE_FILE_PATH = "src/test/resources/test_stakes.txt";

    @BeforeEach
    public void setUp() throws IOException {
        players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        stats = new Statistics();
        game = new Game(players, TEST_WORD_FILE_PATH, TEST_STAKE_FILE_PATH, stats);
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game.getPlayers());
        assertEquals(2, game.getPlayers().size());
        assertEquals(stats, game.getStatistics());
    }

    @Test
    public void testStartGame() {
        game.startGame();
        assertTrue(game.isGameActive());
    }

    @Test
    public void testStartNextRound() {
        game.startGame();
        game.startNextRound();
        assertTrue(game.isGameActive());
    }

    @Test
    public void testPlayRound() {
        game.startGame();
        game.playRound();
        assertFalse(game.isGameActive()); // Ensure the game ends after one full round
    }

    @Test
    public void testEndGame() {
        players.get(0).setWinner(true);  // Mark Player 1 as the winner
        game.endGame(players.get(0));
        assertEquals(1, stats.getGamesWon());
        assertEquals(1, stats.getGamesPlayed());
    }

    @Test
    public void testResetGame() throws IOException {
        game.startGame();
        game.resetGame();
        assertTrue(game.isGameActive());
        assertEquals(0, game.getStatistics().getGamesPlayed());
    }

    @Test
    public void testDetermineWinner() throws Exception {
        players.get(0).addScore(10);  // Player 1 should be the winner
        // Use reflection to call the private method
        Method determineWinnerMethod = Game.class.getDeclaredMethod("determineWinner");
        determineWinnerMethod.setAccessible(true);
        determineWinnerMethod.invoke(game);
    
        assertTrue(players.get(0).isWinner());
        assertFalse(players.get(1).isWinner());
        assertEquals(1, stats.getGamesWon());
    }
}







