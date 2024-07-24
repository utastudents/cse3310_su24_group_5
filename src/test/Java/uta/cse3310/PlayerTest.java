package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Test Player");
    }
    @Test
    public void testPlayerWinner() {
        player.setWinner(true);
        assertEquals(true, player.isWinner());
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("Test Player", player.getName());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testAddScore() {
        player.addScore(100);
        assertEquals(100, player.getScore());
    }

    @Test
    public void testResetScore() {
        player.addScore(100);
        player.resetScore();
        assertEquals(0, player.getScore());
    }
}
