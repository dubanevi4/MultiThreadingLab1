package lt.esdc.multithreading.lab1.task2;

public class T1Thread extends Thread{

    public T1Thread(R1Thread r1Thread) {
        super(r1Thread);
    }

    @Override
    public void run(){
        for (int i = 0; i < MainThreadTask2.COUNTER; i++){
            System.out.println("T1Thread run " +i);
        }
    }
}
