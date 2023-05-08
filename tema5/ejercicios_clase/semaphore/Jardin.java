package tema5.ejercicios_clase.semaphore;

import java.util.concurrent.Semaphore;

public class Jardin {
    private int cont = 0;
    private Semaphore mutex = new Semaphore(1);

    public void inc() throws InterruptedException {
        mutex.acquire();
        cont++;
        mutex.release();
    }

    public int valor() {
        return cont;
    }
}
