package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {

    private Statistics stats;

    @BeforeEach
    public void setUp() {
        stats = new Statistics();
    }

    @Test
    public void testInitialConditions() {
        assertEquals(0, stats.getGamesPlayed());
        assertEquals(0, stats.getGamesWon());
        assertEquals(0, stats.getGamesLost());
    }

    @Test
    public void testIncrementGamesPlayed() {
        stats.incrementGamesPlayed();
        assertEquals(1, stats.getGamesPlayed());
    }

    @Test
    public void testIncrementGamesWon() {
        stats.incrementGamesWon();
        assertEquals(1, stats.getGamesWon());
    }

    @Test
    public void testIncrementGamesLost() {
        stats.incrementGamesLost();
        assertEquals(1, stats.getGamesLost());
    }

    @Test
    public void testUpdateWinner() {
        Player player = new Player("TestPlayer");
        player.setWinner(true);
        stats.updateWinner(player);
        assertEquals(1, stats.getGamesWon());
        assertEquals(0, stats.getGamesLost());

        Player player2 = new Player("TestPlayer2");
        player2.setWinner(false);
        stats.updateWinner(player2);
        assertEquals(1, stats.getGamesWon());
        assertEquals(1, stats.getGamesLost());
    }
}





