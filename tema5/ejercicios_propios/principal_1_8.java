package tema5.ejercicios_propios;

import java.util.Random;

public class principal_1_8 extends Thread {
    // clase para lanzar un numero de hilos dinamicos (sin saber la cantidad de
    // hilos que tendra la persona que ejecute este programa)

    // 'static' necesario para que varios hilos puedan acceder a la variable
    private static int tam = 4;
    private static int[][] matriz = new int[tam][tam];

    private int ini, fin;

    public principal_1_8(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public void run() {
        for (int i = ini; i < fin; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] *= 10;
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());

        // runtime obtiene informacion del hardware donde se esta ejecutando nuestro programa
        // cuidado con el error de runtime, ya que crea una instancia
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();

        System.out.println(nNucleos);

        double tiempo_inicio, tiempo_final;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = rand.nextInt(10);
            }
        }

        // objeto que almacenara el total de los hilos del sistema
        Thread[] hilos = new Thread[nNucleos];
        int rango = tam/nNucleos;

        tiempo_inicio = System.nanoTime();

        // Dividimos el recorrido de la matriz en dos mitades, con el objetivo de evitar
        // el indeterminismo (sobreescritura de los procesos de los hilos).
        principal_1_8 h1 = new principal_1_8(0, 2);
        principal_1_8 h2 = new principal_1_8(2, 4);

        // inicio los hilos
        h1.start();
        h2.start();

        // corrutina (try-catch) para evitar que el hilo principal del main no acabe
        // hasta que acaben los hilos secundarios.
        try {
            h1.join();
            h2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        tiempo_final = System.nanoTime() - tiempo_inicio;
        System.out.println((tiempo_final) / 1000000 + " milisegundos\n");

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
