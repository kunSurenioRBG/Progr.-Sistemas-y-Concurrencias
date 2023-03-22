package tema5.ejercicios_propios;
// Indeterminismo: cuando 2 o mas hilos escriben a la vez en una misma variable compartida, el valor de esta variable es indeterminado.

// Seccion Critica: parte de codigo donde sabemos que se va a producir indeterminismo.

public class principal_1_4 extends Thread {
    private static int cont = 0;

    public void run() {
        for (int i = 0; i < 1000; i++) {
            cont++;
        }
    }

    public static void main(String[] args) {
        principal_1_4 vec[] = new principal_1_4[1000];

        // inicializa el vector en la posicion i
        for (int i = 0; i < vec.length; i++) {
            vec[i] = new principal_1_4();
            vec[i].start();
        }

        // RECORDATORIO
        // JOIN hara que el hilo principal se espera a que los hilos de los subprocesos
        // hayan terminado de ejecutarse.
        try {
            for (int i = 0; i < vec.length; i++) {
                vec[i].join();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(cont);

    }
}

// Muy rara vez va a salir 1.000.000, debido al problema del indeterminismo.
