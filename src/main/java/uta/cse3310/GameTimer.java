package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private transient Timer timer; 
  //  private Timer timer;
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public GameTimer() {
        
        this.timer = new Timer();
        this.elapsedTime = 0;
        this.isRunning = false;
    }

    public void start() {
        if (isRunning) {
            return; // Timer is already running
        }
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTime = System.currentTimeMillis() - startTime;
            }
        }, 0, 1000); // Update elapsed time every second
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
        return elapsedTime / 1000; // Return elapsed time in seconds
    }

    public boolean isRunning() {
        return isRunning;
    }
}


