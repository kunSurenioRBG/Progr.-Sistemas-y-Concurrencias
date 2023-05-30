package examenes.Concurrencias.Rehechos.Semaforos.Junio2020;

import java.util.concurrent.Semaphore;

public class Barca {
	private int nPasajeros;
	private int orilla;

	private Semaphore mutex;
	private Semaphore zarpaBarco;
	private Semaphore bajanPasajeros;
	private Semaphore orillaNorte;
	private Semaphore orillaSur;

	public Barca() {
		mutex = new Semaphore(1);
		zarpaBarco = new Semaphore(0);
		bajanPasajeros = new Semaphore(0);
		orillaNorte = new Semaphore(1);
		orillaSur = new Semaphore(0);
	}

	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public void subir(int id, int pos) throws InterruptedException {
		if (pos == 0) {
			orillaNorte.acquire();
			mutex.acquire();
			nPasajeros++;
			System.out.println("Pasajero " + id + " se ha subido al barco en orilla " + pos + ". Son " + nPasajeros
					+ " pasajeros.");

			if (nPasajeros == 3) {
				zarpaBarco.release();
				orilla = pos;
			} else {
				orillaNorte.release();
			}
			mutex.release();

		} else {
			orillaSur.acquire();
			mutex.acquire();
			nPasajeros++;
			System.out.println("Pasajero " + id + " se ha subido al barco en orilla " + pos + ". Son " + nPasajeros
					+ " pasajeros.");

			if (nPasajeros == 3) {
				zarpaBarco.release();
				orilla = pos;
			} else {
				orillaSur.release();
			}
			mutex.release();
		}

	}

	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException {
		bajanPasajeros.acquire();
		mutex.acquire();
		nPasajeros--;
		System.out.println("Pasajero " + id + " se ha bajado del barco. Quedan " + nPasajeros
				+ " pasajeros.");

		if (nPasajeros > 0) {
			bajanPasajeros.release();
		} else if (orilla % 2 == 0) {
			orillaSur.release();
		} else {
			orillaNorte.release();
		}
		mutex.release();
		return orilla % 2;
	}

	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public void esperoSuban() throws InterruptedException {
		zarpaBarco.acquire();
		System.out.println("Barco lleno: Comienza el viaje!!!");
	}

	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que
	 * bajarse
	 */
	public void finViaje() throws InterruptedException {
		System.out.println("El viaje se ha acabado");
		bajanPasajeros.release();
	}

}
