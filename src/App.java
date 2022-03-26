import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {

        // API do JAVA que trabalha com single ou multithread
        // Lembrar que há duas threads: a principal e a do executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            executor = Executors.newSingleThreadExecutor();
            // executor.execute(new Tarefa()); // executa a tarefa/ thread

            Future<?> future = executor.submit(new Tarefa()); // executa tarefa porém com retorno
            System.out.println(future.isDone()); // a tarefa finalizou?
            executor.shutdown(); // finaliza a tarefa
            executor.awaitTermination(5, TimeUnit.SECONDS); // espera por um tempo antes de finalizar ou para terminar
                                                            // com a última instrução
            System.out.println(future.isDone()); // a tarefa finalizou?

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
