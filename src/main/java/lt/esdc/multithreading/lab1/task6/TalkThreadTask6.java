package lt.esdc.multithreading.lab1.task6;

public class TalkThreadTask6 implements Runnable {
    @Override
    public void run() {
        long startTime = System.nanoTime();
        double result = 0;
        // More CPU-intensive computation to better demonstrate priority differences
        for (int i = 0; i < 10_000_000; i++) {
            result += Math.sqrt(i) * Math.sin(i);
        }
        long endTime = System.nanoTime();
        System.out.println(Thread.currentThread().getName() +
                " (MAX_PRIORITY) finished. Time: " + 
                (endTime - startTime) / 1_000_000.0 + "ms, Result: " + result);
    }
}
