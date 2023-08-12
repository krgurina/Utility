package threads;

public class Program {
    public static void main(String[] args) throws Exception{
//1. просто задание и запуск нескольких потоков
        System.out.println("Main thread started...");
       //создаём и запускаем поток
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join(); //ждем завешения потока myThread
        // или так
//        for(int i = 0;i<3;i++)
//        {
//            new MyThread().start();
//
//        }

        MyThread myThread2 = new MyThread();
        myThread2.start();
        myThread2.join();
        System.out.println("Main thread finished...");
    }
}
