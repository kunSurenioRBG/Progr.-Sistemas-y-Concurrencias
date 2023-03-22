package tema5.ejercicios_propios;

// los hilos se lanzan desde objetos (heredan de la clase Thread)
// los hilos no se tienen que ejecutar cuando se lanzan
// no sabemos el orden de ejecucion de los hilos
public class principal_1_2 extends Thread {
    private int id;

    public principal_1_2(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Soy el hilo:" + id);
    }

    public static void main(String[] args) {
        principal_1_2 h1 = new principal_1_2(1);
        principal_1_2 h2 = new principal_1_2(2);
        principal_1_2 h3 = new principal_1_2(3);

        h1.start(); // genera un nuevo hilo y entra al metodo "run"
        h2.start();
        h3.start();
        // lo que permite es que se ejecute el sout de run y el println de abajo ++a la
        // vez++
        // esa es la idea, ejecutar cosas a la vez

        // en general, el println se ejecutara antes que los hilos, puesto que estos
        // tardan en inicializarse
        System.out.println("Soy el hilo principal");
    }
}
