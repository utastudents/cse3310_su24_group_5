package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    public void testInitialConditions() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testPlayerCreation() {
        assertNotNull(player.getId());
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getScore());
        assertFalse(player.isWinner());
    }

    @Test
    public void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore());
    }

    @Test
    public void testResetScore() {
        player.addScore(10);
        player.resetScore();
        assertEquals(0, player.getScore());
    }

    @Test
    public void testSetAndGetWinner() {
        player.setWinner(true);
        assertTrue(player.isWinner());

        player.setWinner(false);
        assertFalse(player.isWinner());
    }

}
