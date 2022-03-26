import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {

        // API do JAVA que trabalha com single ou multithread
        // Lembrar que há duas threads: a principal e a do executor
        // Neste caso serão utilizadas threads agendadas (3) para uma tarefa de 2
        // segundos
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        // executor.schedule(new Tarefa(), 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new Tarefa(), 0, 2, TimeUnit.SECONDS); // Executa a tarefa instantaneamente(0) em
                                                                            // um período de 2 segundos
        executor.scheduleWithFixedDelay(new Tarefa(), 0, 2, TimeUnit.SECONDS); // Executa as tarefas com um delay entre
                                                                               // a finalização de uma e começo de outra
                                                                               // de 2 segundos

        // PARA FUNCIONAR DEVE-SE COMENTAR O SHUTDOWN!!!
        executor.shutdown(); // Finaliza o executor
    }

    // O try catch é usado para ter certeza que o executor vai ser finalizado
}
