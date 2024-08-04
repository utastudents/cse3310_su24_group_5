package uta.cse3310;

import org.junit.jupiter.api.Test;
import uta.cse3310.Player;
import uta.cse3310.PlayerType;
import uta.cse3310.Round;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    @Test
    public void testStartRound() throws IOException {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1", PlayerType.HUMAN));
        players.add(new Player("Player2", PlayerType.HUMAN));
        Round round = new Round(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt");

        round.startRound();
        assertTrue(round.isRoundActive());
    }

    @Test
    public void testNextTurn() throws IOException {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1", PlayerType.HUMAN));
        players.add(new Player("Player2", PlayerType.HUMAN));
        Round round = new Round(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt");

        round.startRound();
        round.nextTurn();
        assertNotNull(round.getCurrentPlayer());
    }

    @Test
    public void testGetWordsForGame() throws IOException {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1", PlayerType.HUMAN));
        players.add(new Player("Player2", PlayerType.HUMAN));
        Round round = new Round(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt");

        assertNotNull(round.getWordsForGame());
    }
}




