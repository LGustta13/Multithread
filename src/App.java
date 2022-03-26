public class App {
    public static void main(String[] args) throws Exception {

        MeuRunnable runnable = new MeuRunnable();

        Thread t0 = new Thread(runnable); // utiliza as funcionalidades do MeuRunnable
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
