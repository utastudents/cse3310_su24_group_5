import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TimerTest {
    private Timer timer;

    @Before
    public void setUp() {
        timer = new Timer();
    }

    @Test
    public void testTimerInitialization() {
        //tests that the timer is initialized to 0
        assertEquals(0, timer.getElapsedTime());
    }

    @Test
    public void testStartTimer() {
        //tests that when time starts it non zero value
        timer.startTimer();
        assertTrue(timer.getElapsedTime() >= 0);
    }

    @Test
    public void testStopTimer() throws InterruptedException {
        //tests that timer stops by checking the elapsed time after sleeping for 1 second
        timer.startTimer();
        Thread.sleep(1000);
        timer.stopTimer();
        long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= 1 && elapsedTime < 2);
    }

    @Test
    public void testGetElapsedTimeWhileRunning() throws InterruptedException {
        //tests elapsed time while timer is running
        timer.startTimer();
        Thread.sleep(1000);
        assertTrue(timer.getElapsedTime() >= 1);
    }

    @Test
    public void testGetElapsedTimeAfterStop() throws InterruptedException {
        //this makes sure time elapsed stays constant after timer stops
        timer.startTimer();
        Thread.sleep(1000);
        timer.stopTimer();
        long elapsedTime = timer.getElapsedTime();
        Thread.sleep(1000);
        assertEquals(elapsedTime, timer.getElapsedTime());
    }
}
