import java.util.Random;
import java.util.concurrent.Callable;

public class Tarefa implements Callable<String> {

    // callabel seria tipo um runnable porém com retorno
    public String call() throws Exception {
        Thread.sleep(2000);
        String name = Thread.currentThread().getName() + " " + new Random().nextInt(1000);
        return name;
    }
}
