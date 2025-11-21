package lt.esdc.multithreading.lab1.task1;

public class WalkThreadTask1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < MainThreadTask1.COUNTER; i++) {
            System.out.println(Thread.currentThread().getName() + " run " + i);
        }
    }
}
