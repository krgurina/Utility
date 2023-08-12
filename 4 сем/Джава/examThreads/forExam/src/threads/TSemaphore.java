package threads;
import java.util.concurrent.Semaphore;
public class TSemaphore {
    public static void main(String[] args) throws Exception{
        Semaphore sem = new Semaphore(1); // 1 разрешение
        CommonResourse2 resourse = new CommonResourse2();
        resourse.i=5;
        MyThreadSem myThread1 = new MyThreadSem(resourse,sem);
        MyThreadSem myThread2 = new MyThreadSem(resourse,sem);
        MyThreadSem myThread3 = new MyThreadSem(resourse,sem);
//        myThread1.setCommonResourse(resourse);
//        myThread2.setCommonResourse(resourse);
        myThread1.start();
        myThread2.start();
        myThread3.start();
        System.out.println(resourse.i);
    }
}
class MyThreadSem extends Thread{
    CommonResourse2 commonResourse;
    Semaphore sem;
    MyThreadSem(CommonResourse2 res, Semaphore sem){
        this.commonResourse=res;
        this.sem=sem;
    }
//    public void setCommonResourse(CommonResourse2 commonResourse){
//        this.commonResourse=commonResourse;
//    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " ожидает разрешение");
            sem.acquire();
            for (int i = 0; i < 10; i++) {
                commonResourse.ChangeI();
                System.out.printf(Thread.currentThread().getName() + " " + commonResourse.i + "\n");
            }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
        System.out.println(Thread.currentThread().getName() + " освобождает разрешение");
        sem.release();
    }
}

class CommonResourse2{
    int i = 0;
    public void ChangeI(){
        int i = this.i;
        i++;
        this.i=i;
    }
}
