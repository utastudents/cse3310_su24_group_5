package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {
    private Statistics statistics;

    @BeforeEach
    public void setUp() {
        statistics = new Statistics();
    }

    @Test
    public void testIncrementGamesPlayed() {
        statistics.incrementGamesPlayed();
        assertEquals(1, statistics.getGamesPlayed());
    }

    @Test
    public void testIncrementGamesWon() {
        statistics.incrementGamesWon();
        assertEquals(1, statistics.getGamesWon());
    }

    @Test
    public void testIncrementGamesLost() {
        statistics.incrementGamesLost();
        assertEquals(1, statistics.getGamesLost());
    }
}



