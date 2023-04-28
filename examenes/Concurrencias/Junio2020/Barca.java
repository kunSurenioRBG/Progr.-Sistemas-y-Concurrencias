import java.util.concurrent.Semaphore;

public class Barca {
	private Semaphore esperaLleno = new Semaphore(0);
	private int pasajeros = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore finViaje = new Semaphore(0);
	private Semaphore orillaNorte = new Semaphore(1);
	private Semaphore orillaSur = new Semaphore(0);

	private int orilla = 1;
	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public void subir(int id, int pos) throws InterruptedException {

		if (pos == 1) {
			orillaNorte.acquire();
		} else if (pos == 0) {
			orillaSur.acquire();
		}
		mutex.acquire();

		pasajeros++;
		System.out.println("Viajero " + id + " se sube en la orilla " + pos);
		if (pasajeros == 3) {
			System.out.println("El barco sa puesto en marcha :)");
			esperaLleno.release();
		} else {
			if (pos == 1) {
				orillaNorte.release();
			} else if(pos == 0) {
				orillaSur.release();
			}
		}
		mutex.release();
	}

	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException {
		finViaje.acquire();
		mutex.acquire();

		int orillita = orilla;
		pasajeros--;
		System.out.println("Viajero " +id+ " se baja del barco");
		if (pasajeros > 0) {
			finViaje.release();
		} else {
			if (orilla == 1) {
				orillaNorte.release();
				orilla--;
			} else if (orilla == 0) {
				orillaSur.release();
				orilla++;
			}
		}
		mutex.release();

		return orillita == 1 ? 0 : 1; 
	}

	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public void esperoSuban() throws InterruptedException {
		// quien necesita el "esperaLleno"? el capitan --> pues se lo damos al capitan
		esperaLleno.acquire();
		System.out.println("Empieza el viaje");
	}

	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que
	 * bajarse
	 */
	public void finViaje() throws InterruptedException {
		System.out.println("Fin viaje");
		finViaje.release();

	}

}
