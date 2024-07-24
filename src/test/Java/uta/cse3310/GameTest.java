package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    public void setUp() throws Exception {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game = new Game(players, "path/to/words.txt", "path/to/stakes.txt", new Statistics());
    }

    @Test
    public void testGameInitialization() {
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testPlayerScores() {
        player1.addScore(100);
        assertEquals(100, player1.getScore());
        player2.addScore(200);
        assertEquals(200, player2.getScore());
    }
}


