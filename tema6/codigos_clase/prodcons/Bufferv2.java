package tema6.codigos_clase.prodcons;

import java.util.concurrent.Semaphore;

public class Bufferv2 {
    private int b[];
    private int i = 0; // indice de los productores
    private int j = 0; // indice de los consumidores
    // exclusion mutua (un solo proceso acceda a modificar el buffer, es decir, no
    // van a estar tanto en consumidor como el productor tocando el buffer a la vez)
    private Semaphore mutex = new Semaphore(1); // semaforo de exclusion mutua
    private Semaphore turnoCons = new Semaphore(0); // entonces el consumidor extrae
    private Semaphore turnoProd = new Semaphore(1); // entonces el productor inserta

    public Bufferv2(int tam) {
        b = new int[tam];
    }

    public void insertar(int elem, int id) throws InterruptedException {
        turnoProd.acquire();
        mutex.acquire();
        b[i] = elem;
        i = (i + 1) % b.length;
        System.out.println("El productor " + id + " deja " + elem);

        if (i == 0) {
            // no hay datos, consumidor no puede extraer
            turnoCons.release();
        } else {
            // despues de haber metido antes el "elem" en el buffer, libero el hilo
            turnoProd.release();
        }
        mutex.release();
    }

    public int extraer() throws InterruptedException {
        turnoCons.acquire();
        mutex.acquire();

        // dato a extraer
        int dato = 0;
        dato = b[j];

        int elem = b[j];
        j = (j + 1) % b.length;
        System.out.println("Consumidor extrae " + elem);

        if (j == 0) {
            turnoProd.release();
        } else {
            turnoCons.release();
        }
        mutex.release();
        return dato;
    }

}
