package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StakeTest {
    private Stake stake;

    @BeforeEach
    public void setUp() {
        stake = new Stake("initial stakes");
    }

    @Test
    public void testGetStakes() {
        assertEquals("initial stakes", stake.getStakes());
    }

    @Test
    public void testSetStakes() {
        stake.setStakes("new stakes");
        assertEquals("new stakes", stake.getStakes());
    }
}







