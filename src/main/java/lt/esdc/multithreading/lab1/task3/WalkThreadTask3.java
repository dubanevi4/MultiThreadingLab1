package lt.esdc.multithreading.lab1.task3;

public class WalkThreadTask3 extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < MainThreadTask3.COUNTER; i++){
            System.out.println(Thread.currentThread().getName() + " run " + i );
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
