package examenes.Concurrencias.Rehechos.Semaforos.Abril2023.SegundaParte;

import java.util.concurrent.Semaphore;

public class Barca {
	private int N;
	private int maxMenor;
	private int nMenores;
	private int nMayores;

	private Semaphore mutex;
	private Semaphore menoresSuben;
	private Semaphore mayoresSuben;
	private Semaphore esperaBarquero;
	private Semaphore menoresBajan;
	private Semaphore mayoresBajan;

	public Barca(int N, int menoresEnBarca) {
		this.N = N;
		this.maxMenor = menoresEnBarca;
		nMenores = 0;
		nMayores = 0;

		mutex = new Semaphore(1);
		menoresSuben = new Semaphore(1);
		mayoresSuben = new Semaphore(0);
		esperaBarquero = new Semaphore(0);
		menoresBajan = new Semaphore(0);
		mayoresBajan = new Semaphore(0);
	}

	/*
	 *
	 * Nuevo para la segunda versión:
	 * El menor id no puede subirse a la Barca hasta que haya sitio para él,
	 * y se hayan bajado los pasajeros del paseo anterior (si no es el primer paseo)
	 * 
	 * El menor id se sube a la Barca. Si es el último pasajero avisa al Barquero
	 * de que la Barca está completa.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la Barca
	 * 
	 * 
	 * 
	 */
	public void subeMenor(int id) throws InterruptedException {
		// Suben menores
		menoresSuben.acquire();
		mutex.acquire();
		nMenores++;
		System.out.println("El menor " + id + " se ha subido a la barca ");
		if (nMenores == maxMenor) {
			mayoresSuben.release();
		} else {
			menoresSuben.release();
		}
		mutex.release();

		// Bajan Menores
		menoresBajan.acquire();
		mutex.acquire();
		nMenores--;
		System.out.println("El menor " + id + " se ha bajado de la barca");
		if (nMenores == 0) {
			mayoresBajan.release();
		} else {
			menoresBajan.release();
		}
		mutex.release();
	}

	/*
	 * 
	 * Nuevo para la segunda versión:
	 * El Adulto id no puede subirse a la Barca hasta que haya sitio para él y
	 * -se han bajado los pasajeros del paseo anterior (si no es el primer paseo)
	 * -se han subido los numMenor menores a la Barca
	 * 
	 * El adulto id se sube a la Barca. Si es el último pasajero avisa al Barquero
	 * de que la Barca está completa.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la Barca
	 */
	public void subeAdulto(int id) throws InterruptedException {
		// Suben menores
		mayoresSuben.acquire();
		mutex.acquire();
		nMayores++;
		System.out.println("El mayor " + id + " se ha subido a la barca ");
		if (nMenores + nMayores == N) {
			esperaBarquero.release();
		} else {
			mayoresSuben.release();
		}

		mutex.release();

		// Bajan Menores
		mayoresBajan.acquire();
		mutex.acquire();
		nMayores--;
		System.out.println("El mayor " + id + " se ha bajado de la barca");
		if (nMayores == 0) {
			System.out.println("---------------------------------------------------");
			menoresSuben.release();
		} else {
			mayoresBajan.release();
		}
		mutex.release();
	}

	/*
	 * El barquero espera hasta que la Barca tenga N pasajeros para
	 * comenzar el viaje
	 */
	public void esperoLleno() throws InterruptedException {
		esperaBarquero.acquire();
		System.out.println("Comienza el viaje!!!");
	}

	/*
	 * Cuando termina el viaje avisa a los pasajeros que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		System.out.println("Fin del viaje!!!");
		menoresBajan.release();
	}
}

// CS-2v-Menor: Tiene que esperar si no hay sitio libre, o si aún no se han
// bajado los pasajeros del paseo anterior (si no es el primer paseo).
// CS-2v-Adulto: No puede subirse a la Barca hasta que no se han subido los
// Menores.
