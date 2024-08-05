package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {

    private GameTimer gameTimer;

    @BeforeEach
    void setUp() {
        gameTimer = new GameTimer();
    }

    @Test
    void testInitialValues() {
        assertFalse(gameTimer.isRunning());
        assertEquals(0, gameTimer.getElapsedTime());
    }

    @Test
    void testStart() throws InterruptedException {
        gameTimer.start();
        assertTrue(gameTimer.isRunning());
        Thread.sleep(2000); // Sleep for 2 seconds to allow the timer to update
        assertTrue(gameTimer.getElapsedTime() >= 2);
    }

    @Test
    void testStop() throws InterruptedException {
        gameTimer.start();
        Thread.sleep(2000); // Sleep for 2 seconds to allow the timer to update
        gameTimer.stop();
        long elapsed = gameTimer.getElapsedTime();
        assertFalse(gameTimer.isRunning());
        Thread.sleep(1000); // Sleep for 1 second to ensure timer has stopped
        assertEquals(elapsed, gameTimer.getElapsedTime());
    }

    @Test
    void testReset() throws InterruptedException {
        gameTimer.start();
        Thread.sleep(2000); // Sleep for 2 seconds to allow the timer to update
        gameTimer.reset();
        assertFalse(gameTimer.isRunning());
        assertEquals(0, gameTimer.getElapsedTime());
    }
}



