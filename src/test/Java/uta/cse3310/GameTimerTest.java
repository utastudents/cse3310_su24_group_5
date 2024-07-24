package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTimerTest {

    private GameTimer timer;

    @BeforeEach
    public void setUp() {
        timer = new GameTimer();
    }

    @Test
    public void testTimerStart() {
        timer.start();
        assertTrue(timer.isRunning());
    }

    @Test
    public void testTimerStop() {
        timer.start();
        timer.stop();
        assertFalse(timer.isRunning());
    }

    @Test
    public void testTimerElapsedTime() throws InterruptedException {
        timer.start();
        Thread.sleep(1000);
        timer.stop();
        assertTrue(timer.getElapsedTime() >= 1);
    }
}

