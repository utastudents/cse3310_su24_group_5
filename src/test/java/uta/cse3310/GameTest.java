package uta.cse3310;

import org.junit.jupiter.api.Test;
import uta.cse3310.Game;
import uta.cse3310.Player;
import uta.cse3310.PlayerType;
import uta.cse3310.Statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    

    //private
    @Test
    public void testAddPlayer() throws IOException {
        List<Player> players = new ArrayList<>();
        Game game = new Game(players, "src/main/resources/words.txt", "src/main/resources/stake.txt", new Statistics());
                                                   //"src/test/resources/words.txt", "src/main/resources/stake.txt"
        Player player = new Player("Player1", PlayerType.HUMAN);
        assertTrue(game.addPlayer(player));
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void testStartGame() throws IOException {
        List<Player> players = new ArrayList<>();
        Game game = new Game(players, "src/main/resources/words.txt", "src/main/resources/stake.txt", new Statistics());

        game.addPlayer(new Player("Player1", PlayerType.HUMAN));
        game.addPlayer(new Player("Player2", PlayerType.HUMAN));
        game.startGame();
        assertTrue(game.isGameActive());
    }

    @Test
    public void testPlayRound() throws IOException {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player1", PlayerType.HUMAN);
        Player player2 = new Player("Player2", PlayerType.HUMAN);
        players.add(player1);
        players.add(player2);
        Round round = new Round(players, "src/main/resources/words.txt", "src/main/resources/stake.txt");
        round.startRound();
        assertTrue(round.isRoundActive());

    }

    @Test
    public void testDetermineWinner() throws IOException {
        List<Player> players = new ArrayList<>();
        Game game = new Game(players, "src/main/resources/words.txt", "src/main/resources/stake.txt", new Statistics());
        Player player1 = new Player("Player1", PlayerType.HUMAN);
        Player player2 = new Player("Player2", PlayerType.HUMAN);
        player1.addScore(100);
        player2.addScore(50);
        game.addPlayer(player1);
        game.addPlayer(player2);

        game.determineWinner();
        assertEquals(player1.getName(), game.getStatistics().getWinner().getName());
    } 
}









