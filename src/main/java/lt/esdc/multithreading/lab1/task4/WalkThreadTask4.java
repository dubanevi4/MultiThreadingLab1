package lt.esdc.multithreading.lab1.task4;


public class WalkThreadTask4 implements Runnable {
    private String[] strings;

    public WalkThreadTask4(String[] strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        for (String str : strings) {
            System.out.println(Thread.currentThread().getName() + ": " + str);
        }
    }
}
