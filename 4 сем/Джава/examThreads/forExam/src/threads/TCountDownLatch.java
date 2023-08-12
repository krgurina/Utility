package threads;
import java.util.concurrent.CountDownLatch;
public class TCountDownLatch {
    public static void main(String[] args) throws  Exception{
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Work myThread1 = new Work(countDownLatch);
        Work myThread2 = new Work(countDownLatch);
        Work myThread3 = new Work(countDownLatch);
        myThread1.start();
        myThread2.start();
        myThread3.start();

        countDownLatch.await();//ожидаем завершения 3 потоков
        System.out.println("all done");
    }
}

class Work extends Thread{
    CountDownLatch countDownLatch;

    Work(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        try {
            sleep(1500);
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " закончил работу");
        countDownLatch.countDown();
    }
}
