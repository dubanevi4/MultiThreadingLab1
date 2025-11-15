package lt.esdc.multithreading.lab1.task3;

import lt.esdc.multithreading.lab1.task1.WalkThreadTask1;

public class MainThreadTask3 {
    static int COUNTER = 10;

    public static void main(String[] args){

        Thread walkthread = new WalkThreadTask3();
        walkthread.start();

        try {
            walkthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < COUNTER; i++){
            System.out.println(Thread.currentThread().getName() + " run " + i);
        }
    }
}
