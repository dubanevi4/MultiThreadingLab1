package lt.esdc.multithreading.lab1.task8;

/**
 * Main class for Producer/Consumer task
 * 
 * Thread P: Switches true/false with delay M milliseconds
 * Thread C: Waits for true, counts down from K with delay M/10,
 *           pauses when false, stops at zero
 */
public class MainThreadTask8 {
    private static final int M = 100;  // Producer switch delay in milliseconds (reduced for faster testing)
    private static final int K = 1000;  // Consumer countdown start in milliseconds (reduced for faster testing)
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Producer/Consumer Task ===");
        System.out.println("M (Producer delay): " + M + "ms");
        System.out.println("K (Countdown start): " + K + "ms");
        System.out.println("Consumer delay: " + (M/10) + "ms (M/10)");
        System.out.println();
        
        // Record start time
        long startTime = System.currentTimeMillis();
        
        SharedState sharedState = new SharedState();
        
        ProducerThread producer = new ProducerThread(sharedState, M);
        ConsumerThread consumer = new ConsumerThread(sharedState, K, M);
        
        // Start both threads
        producer.start();
        consumer.start();
        
        // Wait for both threads to complete
        producer.join();
        consumer.join();
        
        // Calculate total execution time
        long endTime = System.currentTimeMillis();
        long totalExecutionTime = endTime - startTime;
        
        System.out.println("\n=== Both threads finished ===");
        System.out.println("Final countdown: " + sharedState.getRemainingTime() + "ms");
        System.out.println("Total execution time: " + totalExecutionTime + "ms (" + 
                          String.format("%.2f", totalExecutionTime / 1000.0) + " seconds)");
    }
}

