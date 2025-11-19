package lt.esdc.multithreading.lab1.task8;

/**
 * Shared state between Producer and Consumer threads
 * Uses this object as a single monitor for synchronization
 */
public class SharedState {
    private boolean state = false;  // Current state of Producer
    private int remainingTime = Integer.MAX_VALUE;  // Remaining countdown time (initialized to max to prevent early stop)
    private boolean shouldStop = false;  // Flag to stop both threads
    
    // Single monitor - all synchronized methods use 'this' as monitor
    
    public void setState(boolean newState) {
        synchronized (this) {
            this.state = newState;
            notifyAll();  // Notify all threads waiting on this monitor
        }
    }
    
    public boolean getState() {
        synchronized (this) {
            return state;
        }
    }
    
    public void waitForTrue() throws InterruptedException {
        synchronized (this) {
            while (!state && !shouldStop) {
                wait();  // Wait on this monitor until state becomes true
            }
        }
    }
    
    public int getRemainingTime() {
        synchronized (this) {
            return remainingTime;
        }
    }
    
    public void setRemainingTime(int time) {
        synchronized (this) {
            this.remainingTime = time;
        }
    }
    
    public void decrementTime(int amount) {
        synchronized (this) {
            this.remainingTime -= amount;
            if (this.remainingTime < 0) {
                this.remainingTime = 0;
            }
            if (this.remainingTime <= 0) {
                notifyAll();  // Notify when countdown reaches zero
            }
        }
    }
    
    public boolean shouldStop() {
        synchronized (this) {
            // Only stop if explicitly stopped or if remainingTime has been set and reached zero
            return shouldStop || (remainingTime != Integer.MAX_VALUE && remainingTime <= 0);
        }
    }
    
    public void stop() {
        synchronized (this) {
            this.shouldStop = true;
            notifyAll();  // Wake up all threads waiting on this monitor
        }
    }
}

