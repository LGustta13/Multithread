import java.util.concurrent.atomic.AtomicInteger;

public class MeuRunnable implements Runnable {

    private static AtomicInteger i = new AtomicInteger(-1);

    public void run() {

        // operações atômicas resolvem o problema de concorrência, não há threads
        // competindo pelo recurso. Pode-se utilizar Atomiclong, AtomicBoolean,
        // AtomicReference
        System.out.println(Thread.currentThread().getName() + " " + i.incrementAndGet());
    }
}
