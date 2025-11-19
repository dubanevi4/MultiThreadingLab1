package lt.esdc.multithreading.lab1.task8;

/**
 * Thread P: Switches between true and false with delay M milliseconds
 */
public class ProducerThread extends Thread {
    private final SharedState sharedState;
    private final int M;  // Delay in milliseconds
    
    public ProducerThread(SharedState sharedState, int M) {
        super("Producer");
        this.sharedState = sharedState;
        this.M = M;
    }
    
    @Override
    public void run() {
        boolean currentState = false;
        
        while (!sharedState.shouldStop()) {
            try {
                // Switch state (uses sharedState as monitor)
                currentState = !currentState;
                sharedState.setState(currentState);
                
                System.out.println("Producer: State changed to " + currentState);
                
                // Wait M milliseconds before next switch
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < M && !sharedState.shouldStop()) {
                    Thread.sleep(50);  // Check every 50ms for stop condition
                }
                
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
                break;
            }
        }
        
        System.out.println("Producer: Stopped");
    }
}

