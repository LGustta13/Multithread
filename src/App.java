
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
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
    private static boolean produzindo = true;
    private static boolean consumindo = true;
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        Thread produtor = new Thread(() -> {
            while (true) {
                try {
                    processamento();

                    if (produzindo) {
                        LOCK.lock(); // o lock possibilita que cada thread acesse sua condição e impossibilita que
                                     // outra também entre

                        System.out.println("Produzindo");
                        int numero = new Random().nextInt(10000);
                        FILA.add(numero);

                        if (FILA.size() == 5) {
                            System.out.println("Pausa produtor");
                            produzindo = false;
                        }

                        if (FILA.size() == 1) {
                            System.out.println("inicia consumidor");
                            consumindo = true;
                        }

                        LOCK.unlock();
                    } else {
                        System.out.println("??? Produtor dormindo");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                try {
                    processamento();

                    if (consumindo) {
                        LOCK.lock();

                        System.out.println("Consumindo");
                        Optional<Integer> numero = FILA.stream().findFirst();
                        numero.ifPresent(n -> {
                            FILA.remove(n);
                        });

                        if (FILA.size() == 0) {
                            System.out.println("Pausa consumidor");
                            consumindo = false; // pausa consumidor
                        }

                        if (FILA.size() == 4) {
                            System.out.println("Inicia produtor");
                            produzindo = true; // inicia produtor
                        }

                        LOCK.unlock();
                    } else {
                        System.out.println("??? Consumidor dormindo");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        produtor.start();
        consumidor.start();
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
