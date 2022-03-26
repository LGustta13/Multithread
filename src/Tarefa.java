import java.util.Random;
import java.util.concurrent.Callable;

public class Tarefa implements Runnable {

    // callabel seria tipo um runnable porém com retorno
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + new Random().nextInt(1000));
    }
}
