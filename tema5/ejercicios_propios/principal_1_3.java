package tema5.ejercicios_propios;
// vectores y clase JOIN

public class principal_1_3 extends Thread {
    private int id;

    public principal_1_3(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Soy el hilo: " + id);
    }

    public static void main(String[] args) {
        principal_1_3[] vec = new principal_1_3[5];

        // inicializar cada posicion del vector
        for (int i = 0; i < vec.length; i++) {
            vec[i] = new principal_1_3(i + 1);
            vec[i].start();
        }

        // En general, un hilo se ejecuta de forma aleatoria, sin seguir un orden en
        // especifico.
        // El metodo JOIN garantiza la suspension de subprocesos.
        // Hasta que no se termina de ejecutar los hilos del metodo run, no se lanzaran
        // los de main.
        try {
            for (int i = 0; i < vec.length; i++) {
                vec[i].join();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        /*
         * // lanzar hilo con el objeto en la posicion i del vector
         * for (int i = 0; i < vec.length; i++) {
         * vec[i].start();
         * }
         */

        System.out.println("Soy el hilo principal");
    }
}
