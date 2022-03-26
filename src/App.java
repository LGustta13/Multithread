public class App {
    public static void main(String[] args) throws Exception {
        Thread t = Thread.currentThread(); // executa a thread principal
        System.out.println(t.getName());

        Thread t1 = new Thread(new MeuRunnable());
        t1.start(); // executa a thread do runnable

        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName())); // Função lambda
        t2.start();

        Thread t3 = new Thread(new MeuRunnable());
        t3.start(); // Inicializar
    }
}
