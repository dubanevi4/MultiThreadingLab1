package lt.esdc.multithreading.lab1.task8;

/**
 * Thread P: Switches between true and false with delay M milliseconds
 * State is managed by this thread
 */
public class ProducerThread extends Thread {
    private final SharedState sharedState;
    private final int M;  // Delay in milliseconds
    private boolean state = false;  // State managed by Producer thread
    
    public ProducerThread(SharedState sharedState, int M) {
        super("Producer");
        this.sharedState = sharedState;
        this.M = M;
    }
    
    /**
     * Get current Producer state (synchronized on SharedState monitor)
     */
    public boolean getProducerState() {
        synchronized (sharedState) {
            return state;
        }
    }
    
    /**
     * Set state and notify waiting threads (synchronized on SharedState monitor)
     */
    private void setProducerState(boolean newState) {
        synchronized (sharedState) {
            this.state = newState;
            sharedState.notifyAll();  // Notify all threads waiting on SharedState monitor
        }
    }
    
    @Override
    public void run() {
        while (!sharedState.shouldStop()) {
            try {
                // Switch state
                state = !state;
                setProducerState(state);
                
                System.out.println("Producer: State changed to " + state);

                Thread.sleep(M);  // Sleep with delay M

                
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
                break;
            }
        }
        
        System.out.println("Producer: Stopped");
    }
}

