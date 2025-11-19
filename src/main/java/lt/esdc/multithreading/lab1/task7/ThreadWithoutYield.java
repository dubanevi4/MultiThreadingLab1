package lt.esdc.multithreading.lab1.task7;

public class ThreadWithoutYield implements Runnable {
    @Override
    public void run() {
        long startTime = System.nanoTime();
        double result = 0;
        // Same CPU-intensive computation
        for (int i = 0; i < 10_000_000; i++) {
            result += Math.sqrt(i) * Math.sin(i);
            // Does NOT use yield() - keeps CPU time
        }
        long endTime = System.nanoTime();
        System.out.println(Thread.currentThread().getName() +
                " (WITHOUT yield) finished. Time: " + 
                (endTime - startTime) / 1_000_000.0 + "ms, Result: " + result);
    }
}

