public class MeuRunnable implements Runnable {

    static int i = -1;
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    // Se caso tivermos várias threads, somente uma por vez acessa o método com
    // synchronized! Um de cada vez acessando, os outros esperam a thread atual
    // terminar de usar o recurso (método)
    /*
     * public synchronized void run() { // Cada thread da instância runnable entra
     * aqui
     * i++; // mesmo recurso sendo compartilhado ao mesmo tempo!
     * System.out.println(Thread.currentThread().getName() + " " + i);
     * }
     */

    /*
     * // Aqui eu acabo com o paralelismo
     * public void run() {
     * 
     * synchronized (this) { // this é uma instância de objeto
     * i++; // mesmo recurso sendo compartilhado ao mesmo tempo!
     * System.out.println(Thread.currentThread().getName() + " " + i);
     * }
     * 
     * synchronized (this) {
     * i++; // mesmo recurso sendo compartilhado ao mesmo tempo!
     * System.out.println(Thread.currentThread().getName() + " " + i);
     * }
     * }
     */

    // uso consciente
    public void run() {
        int j;
        synchronized (this) { // o recurso a ser concorrido fica no synchronized!
            i++;
            j = i * 2;
        }
        System.out.println(j);
    }
}
