public class потоки {
    public static void main(String[] args) {
        ThreadGroup group=new ThreadGroup("Group");
        Thread thread1=new Thread(group,"p1");
        ExThread exThread=new ExThread("Max");
        exThread.setPriority(Thread.MAX_PRIORITY);
        Thread thread=new Thread(new ExRannable("Min"));
        thread.setPriority(Thread.MIN_PRIORITY);
         exThread.start();
         thread.start();
        try {
            exThread.join();
            System.out.println(Thread.currentThread().getName());


            new Thread()
            {
                @Override
                public void run() {
                    System.out.println("str 2");
                    Thread.yield();
                    System.out.println("end 2");
                }
            }.start();

            new Thread()
            {
                @Override
                public void run() {
                    System.out.println("str 1");
                    System.out.println("end 1");
                }
            }.start();

            new Thread()
            {
                @Override
                public void run() {
                    System.out.println("str 3");
                    System.out.println("end 3");
                }
            }.start();
        }
        catch (Exception ex)
        {

        }
    }
}

class ExThread extends Thread
{
    String name;
    public ExThread(String name)
    {
        this.name=name;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println("Thread-"+name);
            try {
                Thread.sleep(7);
            } catch (Exception e) {
                System.err.print(e);
            }
        }
    }
}

class ExRannable implements Runnable
{
    String name;
    public ExRannable(String name)
    {
        this.name=name;
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++)
        {
            System.out.println("Ranndable-"+name);
            try {
                Thread.sleep(7);
            }
            catch (Exception e)
            {
                System.err.println(e);
            }
        }
    }
}
