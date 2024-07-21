public class Timer {
    private long startTime;
    private long endTime;
    private boolean running;

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stopTimer() {
        this.endTime = System.currentTimeMillis();
        this.running = false;
    }

    public long getElapsedTime() {
        long elapsedTime;
        if (running) {
            elapsedTime = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsedTime = ((endTime - startTime) / 1000);
        }
        return elapsed;
    }
}
