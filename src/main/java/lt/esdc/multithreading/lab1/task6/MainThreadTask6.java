package lt.esdc.multithreading.lab1.task6;

public class MainThreadTask6 {
    public static void main(String[] args) throws InterruptedException {
        Thread walkMin = new Thread(new WalkThreadTask6(), "Min");
        Thread talkMax = new Thread(new TalkThreadTask6(), "Max");

        walkMin.setPriority(Thread.MIN_PRIORITY);
        talkMax.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Thread priorities:");
        System.out.println("WalkMin (MIN_PRIORITY): " + walkMin.getPriority());
        System.out.println("TalkMax (MAX_PRIORITY): " + talkMax.getPriority());
        System.out.println("Starting threads...\n");

        long startTime = System.nanoTime();

        walkMin.start();
        talkMax.start();

        walkMin.join();
        talkMax.join();

        long endTime = System.nanoTime();
        System.out.println("\nTotal execution time: " + 
            (endTime - startTime) / 1_000_000.0 + "ms");
    }
}
