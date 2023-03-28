package tema5.ejercicios_propios;

import java.util.Random;

//Realizar la multiplicacion de todos los elementos del un vector por 10
public class principal_1_5 extends Thread {
    // Cuando trabajamos con hilos, recomendamos usar 'static', para que varios
    // hilos puedan acceder al mismo vector.
    private static int tam = 8;
    private static int[] vec = new int[tam];

    private int init, fin;

    public principal_1_5(int init, int fin) {
        this.init = init;
        this.fin = fin;
    }

    public void run() {
        for (int i = init; i < fin; i++) { // h1: (init = 0 - fin = 4) / h2: (init = 4 - fin = 8)
            vec[i] *= 10;
        }
        // si lo dejamos asi, el hilo 1, h1, multiplicara todos los valores del vector
        // por 10, pero luego el hilo 2, h2, lo hara tambien
        // por tanto, tenemos un problema de indeterminismo.
        //
        // por ello, inicializamos el constructor y dos variable 'init' y 'fin' y en los
        // hilos marcamos el rango de que mitad cogera cada hilo para multiplicar.
    }

    public static void main(String[] args) {
        // valor aleatorio
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < vec.length; i++) {
            vec[i] = random.nextInt(10); // numero aleatorio entre 0 y (10, en nuestro caso)
        }

        principal_1_5 h1 = new principal_1_5(0, 4);
        principal_1_5 h2 = new principal_1_5(4, 8);

        h1.start();
        h2.start();

        // no va a continuar al hilo principal hasta acabar los hilos de los subprocesos
        try {
            h1.join();
            h2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (int i = 0; i < vec.length; i++) {
            System.out.print(vec[i] + " ");
        }
    }
}
