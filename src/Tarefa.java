public class Tarefa implements Runnable {

    public void run() {

        System.out.println(Thread.currentThread().getName() + " " + "executando");
    }
}
