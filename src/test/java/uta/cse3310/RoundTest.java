package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    private Round round;
    private List<Player> players;

    @BeforeEach
    public void setUp() throws IOException {
        players = new ArrayList<>();
        players.add(new Player("Player1", PlayerType.HUMAN));
        players.add(new Player("Player2", PlayerType.HUMAN));
        round = new Round(players, "src/test/resources/words.txt", "src/main/resources/stake.txt");
    }

    // Helper method to invoke private methods using reflection
    private void invokePrivateMethod(Object instance, String methodName, Object... params) throws Exception {
        Method method = instance.getClass().getDeclaredMethod(methodName, Player.class);
        method.setAccessible(true);
        method.invoke(instance, params);
    }
/* 
    @Test
    public void testPresentOptions() throws Exception {
        // Simulating a choice of buying a vowel
        Method method = Round.class.getDeclaredMethod("handleChoice", Player.class, int.class);
        method.setAccessible(true);
        method.invoke(round, players.get(0), 1); // Simulating choosing option 1 (Buy a vowel)

        // Simulating a choice of selecting a consonant
        method.invoke(round, players.get(0), 2); // Simulating choosing option 2 (Select a consonant)

        // Simulating a choice of solving the puzzle
        method.invoke(round, players.get(0), 3); // Simulating choosing option 3 (Solve the puzzle)
    }*/

    @Test
    public void testBuyVowel() throws Exception {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player1", PlayerType.HUMAN);
        Player player2 = new Player("Player2", PlayerType.HUMAN);
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt", new Statistics());
        Round round = new Round(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt");
        game.startGame();
        round.buyVowel(player1, 'a');
    }

    @Test
    public void testSelectConsonant() throws Exception 
    {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player1", PlayerType.HUMAN);
        Player player2 = new Player("Player2", PlayerType.HUMAN);
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt", new Statistics());
        Round round = new Round(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt");
        game.startGame();
        round.selectConsonant(player1, 'n');
    }

    @Test
    public void testSolvePuzzle() throws Exception {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player1", PlayerType.HUMAN);
        Player player2 = new Player("Player2", PlayerType.HUMAN);
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt", new Statistics());
        Round round = new Round(players, "src/main/resources/wordss.txt", "src/main/resources/stake.txt");
        game.startGame();
        round.solvePuzzle(player1, "applen");
        round.solvePuzzle(player1, "banane");
        round.solvePuzzle(player1, "grapen");
    }
}





