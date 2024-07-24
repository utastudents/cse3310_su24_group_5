import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StakeTest {
    private Stake stake;

    @BeforeEach
    public void setUp() {
        stake = new Stake(100, "Gold");
    }

    @Test
    public void testApplyStake() {
        int result = stake.applyStake();
        assertEquals(100, result); // Check if the applied stake value is correct
    }

    @Test
    public void testGetValue() {
        assertEquals(100, stake.getValue());
    }

    @Test
    public void testSetValue() {
        stake.setValue(200);
        assertEquals(200, stake.getValue());
    }

    @Test
    public void testGetType() {
        assertEquals("Gold", stake.getType());
    }

    @Test
    public void testSetType() {
        stake.setType("Silver");
        assertEquals("Silver", stake.getType());
    }
}

