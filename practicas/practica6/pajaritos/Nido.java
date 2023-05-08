package practicas.practica6.pajaritos;

import java.util.concurrent.Semaphore;

public class Nido {

	private int numB = 0;
	private int B = 4; // buffer
	private Semaphore mutex = new Semaphore(1);
	private Semaphore noVacio = new Semaphore(0);
	private Semaphore noLleno = new Semaphore(0);

	public void come(int id) throws InterruptedException {
		// el bebe id coge un bichito del nido
		noVacio.acquire();
		mutex.acquire();
		numB--;
		System.out.println("El bebé " + id + " ha comido un bichito. Quedan ");

		if (numB > 0) {
			noVacio.release();
		}
		if (numB == B - 1) {
			noLleno.release();
		}
		mutex.release();

	}

	public void nuevoBichito(int id) {
		// el papa/mama id deja un nuevo bichito en el nido

		System.out.println("El papá " + id + " ha añadido un bichito. Hay ");

	}
}

// CS-Bebe-i: No puede comer del nido si está vacío
// CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
