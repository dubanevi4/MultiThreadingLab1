package lt.esdc.multithreading.lab1.task2;

public class R1Thread implements Runnable{

    @Override
    public void run(){
        for (int i = 0; i < MainThreadTask2.COUNTER; i++) {
            System.out.println("R1Thread run " + i);
        }
    }
}
