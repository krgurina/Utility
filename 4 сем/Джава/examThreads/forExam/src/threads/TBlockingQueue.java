package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.PriorityBlockingQueue;
public class TBlockingQueue {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();

        new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName());
                try{
                    System.out.println(blockingQueue.take());
                } catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName());
            }

        }.start();

        new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName());
                blockingQueue.add("Только что добавленная строка");
            }
        }.start();
    }
}
