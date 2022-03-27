import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

// Dedlock, race condition

public class App {

    private static final List<Integer> LISTA = new ArrayList<>(5);
    private static boolean produzindo = true;
    private static boolean consumindo = true;

    public static void main(String[] args) throws Exception {

        Thread produtor = new Thread(() -> {
            while (true) {
                try {
                    processamento();

                    if (produzindo) {
                        System.out.println("Produzindo");
                        int numero = new Random().nextInt(10000);
                        LISTA.add(numero);

                        if (LISTA.size() == 5) {
                            System.out.println("Pausa produtor");
                            produzindo = false;
                        }

                        if (LISTA.size() == 1) {
                            System.out.println("inicia consumidor");
                            consumindo = true;
                        }
                    } else {
                        System.out.println("??? Produtor dormindo");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                try {
                    processamento();

                    if (consumindo) {
                        System.out.println("Consumindo");
                        Optional<Integer> numero = LISTA.stream().findFirst();
                        numero.ifPresent(n -> {
                            LISTA.remove(n);
                        });

                        if (LISTA.size() == 0) {
                            System.out.println("Pausa consumidor");
                            consumindo = false; // pausa consumidor
                        }

                        if (LISTA.size() == 4) {
                            System.out.println("Inicia produtor");
                            produzindo = true; // inicia produtor
                        }
                    } else {
                        System.out.println("??? Consumidor dormindo");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        produtor.start();
        consumidor.start();
    }

    private static final void processamento() {
        int tempo = new Random().nextInt(40);
        try {
            Thread.sleep(tempo);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
