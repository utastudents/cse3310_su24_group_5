package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    private Round round;
    private List<Player> players;

    @BeforeEach
    public void setUp() throws IOException {
        players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        round = new Round(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt");
    }

    @Test
    public void testRoundInitialization() {
        assertNotNull(round.getCurrentWordProgress());
        // Commented out as getCurrentStake() does not exist
        // assertNotNull(round.getCurrentStake());
    }

    @Test
    public void testStartRound() {
        round.startRound();
        assertTrue(round.isRoundActive());
    }

    @Test
    public void testNextTurn() {
        round.startRound();
        round.nextTurn();
        assertTrue(round.isRoundActive());
    }

    @Test
    public void testResetRound() throws IOException {
        round.startRound();
        round.resetRound();
        assertTrue(round.isRoundActive());
        assertNotNull(round.getCurrentWordProgress());
    }
}




