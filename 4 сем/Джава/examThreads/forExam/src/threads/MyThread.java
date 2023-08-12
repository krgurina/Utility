package threads;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.printf("%s Started... \n", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s Finshed... \n", Thread.currentThread().getName());
    }
}
