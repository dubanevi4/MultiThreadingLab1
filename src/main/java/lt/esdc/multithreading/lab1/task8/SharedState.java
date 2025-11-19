package lt.esdc.multithreading.lab1.task8;

/**
 * Shared state between Producer and Consumer threads
 * Uses this object as a single monitor for synchronization
 * State is checked from ProducerThread
 */
public class SharedState {
    private ProducerThread producerThread;  // Reference to Producer to check its state
    private int remainingTime = Integer.MAX_VALUE;  // Remaining countdown time (initialized to max to prevent early stop)
    private boolean shouldStop = false;  // Flag to stop both threads
    
    // Single monitor - all synchronized methods use 'this' as monitor
    
    /**
     * Set reference to ProducerThread
     */
    public void setProducerThread(ProducerThread producerThread) {
        synchronized (this) {
            this.producerThread = producerThread;
        }
    }
    
    /**
     * Get state from ProducerThread (synchronized on this monitor)
     */
    public boolean getState() {
        synchronized (this) {
            if (producerThread != null) {
                return producerThread.getProducerState();
            }
            return false;
        }
    }
    
    /**
     * Wait for Producer state to become true (synchronized on this monitor)
     */
    public void waitForTrue() throws InterruptedException {
        synchronized (this) {
            while (producerThread != null && !producerThread.getProducerState() && !shouldStop) {
                wait();  // Wait on this monitor until Producer state becomes true
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

