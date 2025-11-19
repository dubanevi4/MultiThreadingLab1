package lt.esdc.multithreading.lab1.task5;

import java.util.Arrays;
import java.util.List;

public class MainThreadTask5 {

    static void print(List<String> list) {
        for (String el : list) {
            System.out.println(Thread.currentThread().getName() + ": " + el);
            try {
                Thread.sleep(300);  // Sleep for 300ms
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() +
                        " was interrupted! Exception caught: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a thread that will sleep
        Thread workerThread = new Thread() {
            public void run() {
                print(Arrays.asList("First", "Second", "Third", "Fourth"));
            }
        };

        workerThread.start();

        // Wait a bit to let the thread start sleeping
        Thread.sleep(500);

        // NOW INTERRUPT THE THREAD - this causes InterruptedException!
        System.out.println("Main thread interrupting worker thread...");
        workerThread.interrupt();

        // Wait for the thread to finish
        workerThread.join();

        System.out.println("Main thread finished.");
    }
}
