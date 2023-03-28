package tema5.ejercicios_propios;

// En el paralelismo se ejecutan 2 o mas tareas a la vez, en la concurrencia no.
// En el paralelismo se ejecuta 1 tarea a la vez, mientras que en la concurrencia se ejecutan varias
// Paralelismo mas rapido que la concurrencia

public class principal_1_6 extends Thread {

    private static int cont = 0;

    public void run() {
        // problema de indeterminismo
        for (int i = 0; i < 10000; i++) {
            cont++;
        }
    }

    public static void main(String[] args) {
        principal_1_6 h1 = new principal_1_6();
        principal_1_6 h2 = new principal_1_6();
        principal_1_6 h3 = new principal_1_6();
        principal_1_6 h4 = new principal_1_6();

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(cont);
    }
}
