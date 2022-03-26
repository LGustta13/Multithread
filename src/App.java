import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {

        // API do JAVA que trabalha com single ou multithread
        // Lembrar que há duas threads: a principal e a do executor
        ExecutorService executor = null;

        try {
            executor = Executors.newCachedThreadPool();
            Future<String> f1 = executor.submit(new Tarefa());
            Future<String> f2 = executor.submit(new Tarefa());
            Future<String> f3 = executor.submit(new Tarefa());

            System.out.println(f1.get()); // espera a tarefa finalizar
            System.out.println(f2.get()); // espera a tarefa finalizar
            System.out.println(f3.get()); // espera a tarefa finalizar

            executor.shutdown(); // Finaliza o executor

            System.out.println(f1.isDone());

        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow(); // finaliza o executor de forma agressiva
            }
        }
    }

    // O try catch é usado para ter certeza que o executor vai ser finalizado
}
