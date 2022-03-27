
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Dedlock, race condition, região crítica (onde existe concorrência), mutex (LOCK ou synchronized)
/* Race condition: situação de mais de uma tarefa atrapalhando a outra
Deadlock: uma tarefa esperando por outra tareaf
Sessão crítica: recursos sendo concorridos
Mutex: isolamento das regiões críticas para uma thread em si, nunca mais de uma thread acessa a região crítica*/

public class App {

    private static final BlockingQueue<Integer> FILA = new LinkedBlockingDeque<>(5); // Fila com recursos de sincronismo
                                                                                     // e mutex

    public static void main(String[] args) throws Exception {

        Runnable produtor = (() -> {
            try {
                processamento();

                System.out.println("Produzindo");
                int numero = new Random().nextInt(10000);
                FILA.put(numero); // coloca elemento na fila, se estiver cheia ele espera até liberar espaço

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }

        });

        Runnable consumidor = (() -> {
            try {
                processamento();

                System.out.println("Consumindo");
                FILA.take(); // retira elemento da fila, se estiver vazia espera até aparecer algum

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }

        });

        // Adicionado um executor de threads
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleWithFixedDelay(produtor, 0, 10, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consumidor, 0, 10, TimeUnit.MILLISECONDS);

    }

    private static final void processamento() {
        int tempo = new Random().nextInt(100);
        try {
            Thread.sleep(tempo);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
