import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        game = new Game();
        player1 = new Player("Player 1", 100);
        player2 = new Player("Player 2", 200);
        game.addPlayer(player1, 0);
        game.addPlayer(player2, 1);
    }

    @Test
    public void testStartGame() {
        game.startGame();
        assertNotNull(game.getCurrentRound());
        assertTrue(game.getRounds().contains(game.getCurrentRound()));
    }

    @Test
    public void testEndGame() {
        game.startGame();
        game.endGame();
        // Add assertions to verify the endGame logic if necessary
    }

    @Test
    public void testCalculateWinner() {
        int winnerIndex = game.calculateWinner();
        assertEquals(1, winnerIndex); // Player 2 has the highest score
    }

    @Test
    public void testAddPlayer() {
        Player player3 = new Player("Player 3", 150);
        game.addPlayer(player3, 2);
        assertEquals(player3, game.getPlayer(2));
    }

    @Test
    public void testGetPlayer() {
        assertEquals(player1, game.getPlayer(0));
        assertEquals(player2, game.getPlayer(1));
    }
}

