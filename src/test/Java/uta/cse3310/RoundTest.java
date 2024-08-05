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
        round = new Round(players, "src/test/resources/test_words.txt", "src/test/resources/test_stakes.txt");
    }

    // Helper method to invoke private methods using reflection
    private void invokePrivateMethod(Object instance, String methodName, Object... params) throws Exception {
        Method method = instance.getClass().getDeclaredMethod(methodName, Player.class);
        method.setAccessible(true);
        method.invoke(instance, params);
    }

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
    }

    @Test
    public void testBuyVowel() throws Exception {
        // Simulate buying a vowel
        Method method = Round.class.getDeclaredMethod("buyVowel", Player.class);
        method.setAccessible(true);
        method.invoke(round, players.get(0)); // Simulating buying a vowel

        // Add assertions here if necessary to check the effects of the input
        assertTrue(players.get(0).getScore() <= 1000); // Just a sample assertion
    }

    @Test
    public void testSelectConsonant() throws Exception {
        // Simulate selecting a consonant
        Method method = Round.class.getDeclaredMethod("selectConsonant", Player.class);
        method.setAccessible(true);
        method.invoke(round, players.get(0)); // Simulating selecting a consonant

        // Add assertions here if necessary to check the effects of the input
        assertTrue(players.get(0).getScore() >= 0); // Just a sample assertion
    }

    @Test
    public void testSolvePuzzle() throws Exception {
        // Simulate solving the puzzle
        Method method = Round.class.getDeclaredMethod("solvePuzzle", Player.class);
        method.setAccessible(true);
        method.invoke(round, players.get(0)); // Simulating solving the puzzle

        // Add assertions here if necessary to check the effects of the input
        assertTrue(players.get(0).isWinner()); // Just a sample assertion
    }
}





