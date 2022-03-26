import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

// Explicando, várias threads estão tentando acessar o mesmo recurso, que é adicionar elementos na coleção
public class MeuRunnable implements Runnable {

    // private static List<String> lista = new
    // CopyOnWriteArrayList<>(); // Coleção thread-safe
    // private static Map<Integer, String> mapa = new ConcurrentHashMap<>();
    private static Queue<String> fila = new LinkedBlockingQueue<>();

    public void run() {
        // lista.add("Inserido!");
        // mapa.put(new Random().nextInt(), "olá!"); // neste momento o recurso mapa
        // está sincronizado
        fila.add("no topo");
        System.out.println(Thread.currentThread().getName());

    }

    public static Queue<String> getList() {
        return fila;
    }
}
