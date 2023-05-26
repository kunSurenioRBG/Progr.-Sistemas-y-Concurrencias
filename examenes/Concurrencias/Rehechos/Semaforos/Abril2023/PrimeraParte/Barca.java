package examenes.Concurrencias.Rehechos.Semaforos.Abril2023.PrimeraParte;

import java.util.concurrent.Semaphore;

public class Barca {
	private int N;
	private int nMenores;
	private int nMayores;
	private Semaphore mutex;
	private Semaphore barqueroPoneMarcha;
	private Semaphore bajaMenor;
	private Semaphore bajaMayor;

	public Barca(int N) {
		this.N = N; // capacidad de la Barca
		nMenores = 0;
		nMayores = 0;
		mutex = new Semaphore(1);
		barqueroPoneMarcha = new Semaphore(0);
		bajaMayor = new Semaphore(0);
		bajaMenor = new Semaphore(0);
	}

	/*
	 * El menor id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta
	 * versión,
	 * un menor que quiera subir podrá hacerlo sin tener que esperar.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeMenor(int id) throws InterruptedException {
		// Se suben a la barca
		mutex.acquire();
		nMenores++;
		System.out.println("El menor " + id + " se ha subido a la barca");
		if (nMenores + nMayores == N) {
			barqueroPoneMarcha.release();
		}
		mutex.release();

		// Se bajan de la barca
		bajaMenor.acquire();
		mutex.acquire();
		System.out.println("El menor " + id + " se ha bajado de la barca");
		nMenores--;
		if (nMenores == 0) {
			bajaMayor.release();
		} else {
			bajaMenor.release();
		}
		mutex.release();
	}

	/*
	 * El adulto id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta
	 * versión, un adulto que
	 * quiera subir podrá hacerlo sin tener que esperar.
	 * A continuación, espera hasta que finalice el paseo.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeAdulto(int id) throws InterruptedException {
		// Se suben a la barca
		mutex.acquire();
		nMayores++;
		System.out.println("El mayor " + id + " se ha subido a la barca");
		if (nMenores + nMayores == N) {
			barqueroPoneMarcha.release();
		}
		mutex.release();

		// Se bajan de la barca
		bajaMayor.acquire();
		mutex.acquire();
		System.out.println("El mayor " + id + " se ha bajado de la barca");
		nMayores--;
		bajaMayor.release();
		mutex.release();
	}

	/*
	 * El barquero espera hasta que la barca tenga N pasajeros para
	 * comenzar el paseo
	 */
	public void esperoLleno() throws InterruptedException {
		barqueroPoneMarcha.acquire();
		System.out.println("Comienza el viaje!!!");
	}

	/*
	 * Cuando termina el paseo, el barquero avisa a los excusionistas que se pueden
	 * bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() throws InterruptedException {
		System.out.println("Fin del viaje!!!");
		if (nMenores == 0) {
			bajaMayor.release();
		} else {
			bajaMenor.release();
		}
	}
}

// CS-Barquero: El barquero no pone en marcha la barca hasta que no se han
// subido N excursionistas
// CS-Menor: Un menor que está en la barca no puede bajarse hasta que no se ha
// terminado el paseo.
// CS-Adulto: Un adulto que está en la barca no puede bajarse hasta que no se ha
// terminado el paseo
// y no se hayan bajado todos los menores (en caso de que se hubiera subido
// alguno).
