package lt.esdc.multithreading.lab1.task2;

public class T2Thread extends Thread{
    private R1Thread r1thread;

    public T2Thread (R1Thread r1Thread){
        super(r1Thread);
        this.r1thread=r1Thread;
    }

    @Override
    public void run(){
        r1thread.run();
    }
}
