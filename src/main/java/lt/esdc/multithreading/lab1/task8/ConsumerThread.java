package lt.esdc.multithreading.lab1.task8;

/**
 * Thread C: Waits for true state, counts down from K with delay M/10,
 * pauses when state is false, stops at zero
 */
public class ConsumerThread extends Thread {
    private final SharedState sharedState;
    private final int K;  // Initial countdown time in milliseconds
    private final int M;  // Used to calculate delay (M/10)
    
    public ConsumerThread(SharedState sharedState, int K, int M) {
        super("Consumer");
        this.sharedState = sharedState;
        this.K = K;
        this.M = M;
    }
    
    @Override
    public void run(){
        sharedState.setRemainingTime(K);
        int delay = Math.max(1, M / 10);  // Delay between countdown steps
        
        System.out.println("Consumer: Starting countdown from " + K + "ms with delay " + delay + "ms");
        
        while (!sharedState.shouldStop()) {
            try {
                // Wait for Producer to be in true state (uses sharedState as monitor)
                sharedState.waitForTrue();
                
                if (sharedState.shouldStop()) {
                    break;
                }
                
                // Countdown while state is true
                while (!sharedState.shouldStop()) {
                    if (!sharedState.getState()) {
                        break;
                    }
                    int remaining = sharedState.getRemainingTime();
                    
                    if (remaining <= 0) {
                        System.out.println("Consumer: Countdown reached ZERO!");
                        sharedState.stop();
                        break;
                    }
                    
                    System.out.println("Consumer: Countdown = " + remaining + "ms");
                    
                    // Ensure producer is still true before consuming time slice
                    if (!sharedState.getState()) {
                        System.out.println("Consumer: Paused (Producer state is false)");
                        break;
                    }
                    
                    // Decrement by delay amount and wait with delay M/10
                    sharedState.decrementTime(delay);
                    Thread.sleep(delay);
                    // State became false, pause
                    if (!sharedState.getState() && !sharedState.shouldStop()) {
                        System.out.println("Consumer: Paused (Producer state is false)");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
                break;
            }
        }
        
        System.out.println("Consumer: Stopped. Final countdown: " + sharedState.getRemainingTime() + "ms");
    }
}

