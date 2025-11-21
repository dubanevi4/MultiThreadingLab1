package lt.esdc.multithreading.lab1.task2;

public class MainThreadTask2 {
    public static final int COUNTER = 10;

    public static void main(String[] args){

        Thread t1thread = new T1Thread(new R1Thread());
        Thread t2thread = new T2Thread(new R1Thread());
        t1thread.start();
//        t2thread.start();
    }
}
