package lecesc;

import java.util.concurrent.*;

public class GestorBD { // Recurso
	// solucion injusta para los escritores

	private int nLectores = 0;
	private Semaphore escribiendo = new Semaphore(1);
	private Semaphore mutex1 = new Semaphore(1);// para nLectores
	private Semaphore mutex2 = new Semaphore(1);// para nEscritores
	private Semaphore paroLectores = new Semaphore(1);
	private int nEscritores = 0;

	public void entraLector(int id) throws InterruptedException {

		mutex.acquire();
		nLectores++;
		if (nLectores == 1)
			escribiendo.acquire();
		System.out.println("Lector " + id + " entra en la BD " + nLectores);

		mutex.release();
	}

	public void saleLector(int id) throws InterruptedException {
		// usado por el lector id para salir de la BD

		mutex.acquire();

		nLectores--;
		System.out.println("Lector " + id + " sale en la BD " + nLectores);
		if (nLectores == 0)
			escribiendo.release();
		mutex.release();

	}

	public void entraEscritor(int id) throws InterruptedException {
		// usado por el escritor id para acceder a la BD
		escribiendo.acquire();
		System.out.println("Escritor " + id + " entra en la BD " + nLectores);
	}

	public void saleEscritor(int id) {
		// usado por el lector id para salir de la BD

		System.out.println("Escritor " + id + " sale en la BD " + nLectores);
		escribiendo.release();
	}
}

// CS-E: exclusión mutua para el uso de la BD
// CS-L: puede haber cualquier número de lectores en la BD