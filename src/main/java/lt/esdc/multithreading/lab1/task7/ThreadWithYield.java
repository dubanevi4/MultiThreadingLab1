package lt.esdc.multithreading.lab1.task7;

public class ThreadWithYield implements Runnable {
    @Override
    public void run() {
        long startTime = System.nanoTime();
        double result = 0;
        // CPU-intensive computation
        for (int i = 0; i < 10_000_000; i++) {
            result += Math.sqrt(i) * Math.sin(i);
            // Uses yield() - gives up CPU time to other threads
            Thread.yield();
        }
        long endTime = System.nanoTime();
        System.out.println(Thread.currentThread().getName() +
                " (WITH yield) finished. Time: " + 
                (endTime - startTime) / 1_000_000.0 + "ms, Result: " + result);
    }
}

