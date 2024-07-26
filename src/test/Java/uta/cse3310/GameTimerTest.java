package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTimerTest {

    private GameTimer gameTimer;

    @BeforeEach
    public void setUp() {
        gameTimer = new GameTimer();  // Ensure the variable name is consistent
    }

    @Test
    public void testInitialConditions() {
        assertEquals(0, gameTimer.getElapsedTime());
        assertFalse(gameTimer.isRunning());
    }

    @Test
    public void testStart() {
        gameTimer.start();  // Use the correct variable name
        assertTrue(gameTimer.isRunning());
    }

    @Test
    public void testStop() {
        gameTimer.start();  // Use the correct variable name
        gameTimer.stop();
        assertFalse(gameTimer.isRunning());
    }

    @Test
    public void testReset() {
        gameTimer.start();  // Use the correct variable name
        gameTimer.reset();
        assertFalse(gameTimer.isRunning());
        assertEquals(0, gameTimer.getElapsedTime());
    }

    @Test
    public void testStartWhileRunning() throws InterruptedException {
        gameTimer.start();  // Use the correct variable name
        Thread.sleep(2000); // Sleep for 2 seconds to allow the timer to run

        long elapsedTime = gameTimer.getElapsedTime();
        gameTimer.start(); // Attempt to start the timer again while it's running

        assertTrue(gameTimer.isRunning());
        Thread.sleep(1000); // Sleep for 1 second to check if the timer continues

        assertTrue(gameTimer.getElapsedTime() >= elapsedTime + 1);
        gameTimer.stop(); // Stop the timer to clean up for other tests
    }

    @Test
    public void testStopWhileStopped() {
        gameTimer.stop(); // Attempt to stop the timer while it's not running

        assertFalse(gameTimer.isRunning());
        assertEquals(0, gameTimer.getElapsedTime());
    }
}



