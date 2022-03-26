import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class App {

    private static BlockingQueue<Double> resultado = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws Exception {

        // API do JAVA que trabalha com single ou multithread
        // Lembrar que há duas threads: a principal e a do executor
        // Cyclic_Barrier: usado quando queremos que as threads esperem uma pela outra
        // em um determinado ponto na tarefa. As threads chamam a barreira para esperar.
        // ponto de sincronia entre múltiplas execuções, por fim uma tarefa é colocada
        // como parâmetro para ser executada

        Runnable res = () -> {
            double resultadoFinal = 0;
            resultadoFinal += resultado.poll();
            resultadoFinal += resultado.poll();
            resultadoFinal += resultado.poll();
            System.out.println("Resultado: " + resultadoFinal);
        };

        CyclicBarrier ciclo = new CyclicBarrier(3, res);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> { // tarefa que pode ser executada por uma thread
            resultado.add(432d * 3d);
            await(ciclo); // espera pelas outras threads
        };
        Runnable r2 = () -> {
            resultado.add(Math.pow(3d, 14d));
            await(ciclo);
        };
        Runnable r3 = () -> {
            resultado.add(45d * 127d / 12d);
            await(ciclo);
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        executor.awaitTermination(200, TimeUnit.MILLISECONDS);

        executor.shutdown();
    }

    public static void await(CyclicBarrier ciclo) {
        try {
            ciclo.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    // O try catch é usado para ter certeza que o executor vai ser finalizado
}
