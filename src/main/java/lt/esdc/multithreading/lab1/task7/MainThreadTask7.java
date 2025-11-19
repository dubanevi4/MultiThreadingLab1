package lt.esdc.multithreading.lab1.task7;

public class MainThreadTask7 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadWithYield = new Thread(new ThreadWithYield(), "ThreadWithYield");
        Thread threadWithoutYield = new Thread(new ThreadWithoutYield(), "ThreadWithoutYield");

        System.out.println("Starting two threads:");
        System.out.println("1. ThreadWithYield - uses Thread.yield() in loop");
        System.out.println("2. ThreadWithoutYield - does NOT use yield()");
        System.out.println("Both threads perform the same computation.\n");

        long startTime = System.nanoTime();

        threadWithYield.start();
        threadWithoutYield.start();

        threadWithYield.join();
        threadWithoutYield.join();

        long endTime = System.nanoTime();
        System.out.println("\nTotal execution time: " + 
            (endTime - startTime) / 1_000_000.0 + "ms");
        
        System.out.println("\nExplanation:");
        System.out.println("Thread.yield() suggests to the scheduler that the current thread");
        System.out.println("is willing to give up its current use of CPU. The thread without");
        System.out.println("yield() will typically finish faster because it doesn't voluntarily");
        System.out.println("give up CPU time.");
    }
}

