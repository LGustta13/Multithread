import java.util.ArrayList;

public class MeuRunnable implements Runnable {

    private static ArrayList<String> lista = new ArrayList<>(); // listas tem problemas com trabalhar com threads!
    // lista = Collections.synchronizedList(lista);
    // utilizar a versão synchronized de acordo com o tipo0 de coleção (MAP, SET,
    // LIST, COLLECTION)

    public void run() {
        lista.add("Inserido!");
        System.out.println(Thread.currentThread().getName());

    }

    public static ArrayList<String> getList() {
        return lista;
    }
}
