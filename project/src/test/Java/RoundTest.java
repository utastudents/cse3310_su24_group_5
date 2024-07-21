import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private Round round;

    @BeforeEach
    public void setUp() {
        round = new Round();
    }

    @Test
    public void testStartRound() {
        round.startRound();
        assertEquals(0, round.getTurns());
        assertTrue(round.getWords().isEmpty());
        assertTrue(round.getStakes().isEmpty());
    }

    @Test
    public void testEndRound() {
        round.endRound();
        // Add assertions to verify the endRound logic if necessary
    }

    @Test
    public void testSelectWords() {
        Word word1 = new Word("example");
        round.addWord(word1);
        int result = round.selectWords();
        assertEquals(0, result); // Assuming the first word is selected
    }

    @Test
    public void testSelectStake() {
        Stake stake1 = new Stake();
        stake1.setValue(100);
        round.addStake(stake1);
        int result = round.selectStake();
        assertEquals(0, result); // Assuming the first stake is selected
    }
}
