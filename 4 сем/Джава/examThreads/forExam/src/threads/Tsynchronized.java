package threads;

public class Tsynchronized {
    public static void main(String[] args) throws Exception{
        CommonResourse resourse = new CommonResourse();
        resourse.i=5;
        MyThreadS myThread1 = new MyThreadS();
        MyThreadS myThread2 = new MyThreadS();
        myThread1.setCommonResourse(resourse);
        myThread2.setCommonResourse(resourse);
        myThread1.start();
        myThread2.start();
        myThread1.join();
        myThread2.join();
        System.out.println(resourse.i);
    }


}

class MyThreadS extends Thread{
    CommonResourse commonResourse;
    public void setCommonResourse(CommonResourse commonResourse){
        this.commonResourse=commonResourse;
    }
    @Override
    public void run() {
        synchronized(commonResourse){
            for (int i = 0; i < 20; i++) {
                commonResourse.ChangeI();
                System.out.printf(Thread.currentThread().getName() + " " + commonResourse.i + "\n");
            }
        }



    }
}

class CommonResourse{
    int i = 0;
    public void ChangeI(){  //для того чтобы потоки по 1 получали доступ к этому методу - public synchronized void ChangeI(){
            int i = this.i;
            i++;
            this.i=i;
    }
}
