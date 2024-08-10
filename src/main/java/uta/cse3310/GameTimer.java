package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private transient Timer timer;
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;
    private final long timeLimit;

    public GameTimer(long timeLimit) {
        this.timer = new Timer();
        this.timeLimit = timeLimit; // Time limit in milliseconds
        this.elapsedTime = 0;
        this.isRunning = false;
    }

    public void start() {
        if (isRunning) {
            return; // Timer is already running
        }
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                elapsedTime = System.currentTimeMillis() - startTime;
                if (getRemainingTime() <= 0) {
                    stop();
                    // Handle timer expiration (e.g., advance the turn)
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000); // Update elapsed time every second
    }

    public void stop() {
        if (!isRunning) {
            return; // Timer is already stopped
        }
        this.timer.cancel();
        this.elapsedTime = System.currentTimeMillis() - startTime;
        this.isRunning = false;
    }

    public void reset() {
        this.timer.cancel();
        this.timer = new Timer();
        this.elapsedTime = 0;
        this.isRunning = false;
    }

    public long getElapsedTime() {
        return elapsedTime; // Elapsed time in milliseconds
    }

    public long getRemainingTime() {
        return timeLimit - getElapsedTime(); // Remaining time in milliseconds
    }

    public boolean isRunning() {
        return isRunning;
    }
}
