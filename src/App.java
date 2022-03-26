import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

    private static final Semaphore SEMAFORO1 = new Semaphore(10);
    private static final Semaphore SEMAFORO2 = new Semaphore(3);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {

            try {
                SEMAFORO1.acquire();
                System.out.println(new Random().nextInt(10000) + ", " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                SEMAFORO1.release();
            }
        };

        Runnable r2 = () -> {

            try {
                boolean conseguiu = false;
                while (!conseguiu) {
                    conseguiu = SEMAFORO2.tryAcquire(1, TimeUnit.SECONDS);
                }
                System.out.println(new Random().nextInt(10000) + ", " + Thread.currentThread().getName());
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } finally {
                SEMAFORO2.release();
            }
        };

        for (int i = 0; i < 500; i++) {
            // executor.execute(r1);
            executor.execute(r2);

        }

        executor.shutdown();

    }

    // O try catch é usado para ter certeza que o executor vai ser finalizado
}
