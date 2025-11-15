package lt.esdc.multithreading.lab1.task1;


public class MainThreadTask1 {
    static int COUNTER = 10;

    public static void main(String[] args){

        Thread walkthread = new WalkThreadTask1();
        walkthread.start();
        for (int i = 0; i < COUNTER; i++){
            System.out.println(Thread.currentThread().getName() + " run " + i );
        }
    }
}
