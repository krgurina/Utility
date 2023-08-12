package threads;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class TCyclicBarier {
    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new Run());
        Sportsman sportsman1 = new Sportsman(cyclicBarrier);
        Thread.sleep(1000);
        Sportsman sportsman2 = new Sportsman(cyclicBarrier);
        Thread.sleep(1000);
        Sportsman sportsman3 = new Sportsman(cyclicBarrier);
    }
}

class Run extends Thread{   //поток который запуститься когда все 3 потока спортсмена соберутся вместе
    @Override
    public void run(){
        System.out.println("все спортсмены в сборе можно начинать");
    }
}
class Sportsman extends Thread{
    CyclicBarrier cyclicBarrier;
    Sportsman(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
        start();
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
        try{
            cyclicBarrier.await();
        } catch(InterruptedException | BrokenBarrierException e){
            System.out.println(e.getMessage());
        }
    }

}
