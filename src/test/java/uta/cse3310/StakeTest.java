package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StakeTest {

    private Stake stake;
    private String filePath = "src/main/resources/stake.txt";

    @BeforeEach
    void setUp() throws IOException {
        stake = new Stake(filePath);
    }

    @Test
    void testDefaultConstructor() {
        assertNull(stake.getCurrentStake());
    }

    

    @Test
    void testParameterizedConstructor() throws IOException {
        Stake stakeWithParam = new Stake(filePath);
        //assertEquals("High", stakeWithParam.getCurrentStake());
        assertNotNull(stake.getStakes(), "Stakes list should not be null");
    }

    @Test
    void testGetCurrentStake() {
        stake.setCurrentStake("Medium");
        assertEquals("Medium", stake.getCurrentStake());
    }

    @Test
    void testSetCurrentStake() {
        stake.setCurrentStake("Low");
        assertEquals("Low", stake.getCurrentStake());
    }

    @Test
    void testReset() {
        stake.setCurrentStake("Medium");
        stake.resetcurrentStake();
        assertNull(stake.getCurrentStake());
    }

    @Test
    void testCalculatePoints() {
        int points = stake.calculatePoints("Extra Turn", 'a');
        assertEquals(10, points); // Adjust based on actual calculation logic
    }
}









