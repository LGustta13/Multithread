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
            executor = Executors.newSingleThreadExecutor();
            // executor.execute(new Tarefa()); // executa a tarefa/ thread

            Future<String> future = executor.submit(new Tarefa()); // executa tarefa porém com retorno
            System.out.println(future.isDone()); // a tarefa finalizou?
            System.out.println(future.get(1, TimeUnit.SECONDS)); // espera a tarefa finalizar, ou espera no máx x seg
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
