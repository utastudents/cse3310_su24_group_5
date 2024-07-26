package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StakeTest {

    private Stake stake;

    @BeforeEach
    public void setUp() {
        stake = new Stake();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(stake.getCurrentStake());
    }

    @Test
    public void testConstructorWithArgument() {
        Stake stakeWithArgument = new Stake("High");
        assertEquals("High", stakeWithArgument.getCurrentStake());
    }

    @Test
    public void testGetAndSetCurrentStake() {
        stake.setCurrentStake("Low");
        assertEquals("Low", stake.getCurrentStake());

        stake.setCurrentStake("Medium");
        assertEquals("Medium", stake.getCurrentStake());
    }

    @Test
    public void testReset() {
        stake.setCurrentStake("High");
        stake.reset();
        assertNull(stake.getCurrentStake());
    }
}








