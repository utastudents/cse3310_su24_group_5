package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics statistics;
    private Player winner;
    private Player loser;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
        winner = new Player("Winner", PlayerType.HUMAN);
        loser = new Player("Loser", PlayerType.HUMAN);
    }

    @Test
    void testInitialValues() {
        assertEquals(0, statistics.getGamesPlayed());
        assertEquals(0, statistics.getGamesWon());
        assertEquals(0, statistics.getGamesLost());
    }

    @Test
    void testIncrementGamesPlayed() {
        statistics.incrementGamesPlayed();
        assertEquals(1, statistics.getGamesPlayed());
    }

    @Test
    void testIncrementGamesWon() {
        statistics.incrementGamesWon();
        assertEquals(1, statistics.getGamesWon());
    }

    @Test
    void testIncrementGamesLost() {
        statistics.incrementGamesLost();
        assertEquals(1, statistics.getGamesLost());
    }

    @Test
    void testUpdateWinner() {
        winner.setWinner(true);
        statistics.updateWinner(winner);
        assertEquals(1, statistics.getGamesWon());
        assertEquals(0, statistics.getGamesLost());

        loser.setWinner(false);
        statistics.updateWinner(loser);
        assertEquals(1, statistics.getGamesWon());
        assertEquals(1, statistics.getGamesLost());
    }
}






