package threads;

import java.util.concurrent.Phaser;
/*
* Phaser определяет объект синхронизации, который ждет, пока не
завершится определенная фаза. Затем Phaser переходит к следующей стадии
или фазе и снова ожидает ее завершения*/

/*
* 2 челика одновременно моют машины
* но у них 1 гараж(это фаза)
* */
public class TPhaser {
    public static void main(String[] args) throws Exception{
        Phaser phaser = new Phaser(2);//одновременно в фазе может быть 2 объекта
        new Washer(phaser);
        new Washer(phaser);
    }
}

class Washer extends Thread{   //поток который запуститься когда все 3 потока спортсмена соберутся вместе
    Phaser phaser;
    Washer(Phaser phaser){
        this.phaser=phaser;
        start();
    }
    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println(getName()+ " моет машину");
            phaser.arriveAndAwaitAdvance();
        }
    }
}

