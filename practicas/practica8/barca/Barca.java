package barca;

public class Barca extends Thread {
	private static final int C = 4;
	private int numAndroid, numIphone;
	private boolean puedeSubir, puedeBajar;

	public Barca() {
		numAndroid = 0;
		numIphone = 0;
		puedeSubir = true;
		puedeBajar = false;
	}

	/**
	 * Un estudiante con mÃ³vil android llama a este mÃ©todo
	 * cuando quiere cruzar el rÃ­o. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * mÃ©todo
	 * 
	 * @param id del estudiante android que llama al mÃ©todo
	 * @throws InterruptedException
	 */
	public synchronized void android(int id) throws InterruptedException {
		// CS (condicion de sincronizacion) para subir
		while (!puedeSubir || !esSegura(numAndroid + 1, numIphone)) {
			wait();
		}

		numAndroid++;
		System.out.println("Android " + id + " sube");

		if (numAndroid + numIphone == C) {
			// inicia el viaje
			puedeSubir = false; // no se puede subir mas gente
			System.out.println("EMPIEZA EL VIAJE");

			System.out.println("TERMINA EL VIAJE");
			puedeBajar = true;
			notifyAll(); 
		} else {
			while (!puedeBajar) {
				wait();
			}
		}

		numAndroid--;
		System.out.println("Android " + id + " baja");
		if (numAndroid + numIphone == 0) { // se han bajado todos
			puedeSubir = true;
			puedeBajar = false;
			notifyAll();
			System.out.println("Todos abajo. Barca vacia");
		}
	}

	/**
	 * Un estudiante con mÃ³vil android llama a este mÃ©todo
	 * cuando quiere cruzar el rÃ­o. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * mÃ©todo
	 * 
	 * @param id del estudiante android que llama al mÃ©todo
	 * @throws InterruptedException
	 */

	public synchronized void iphone(int id) throws InterruptedException {
		// CS (condicion de sincronizacion) para subir
		while (!puedeSubir || !esSegura(numAndroid, numIphone + 1)) {
			wait();
		}

		numIphone++;
		System.out.println("Iphone " + id + " sube");

		if (numAndroid + numIphone == C) {
			// inicia el viaje
			puedeSubir = false; // no se puede subir mas gente
			System.out.println("EMPIEZA EL VIAJE");

			System.out.println("TERMINA EL VIAJE");
			puedeBajar = true;
			notifyAll(); 
		} else {
			while (!puedeBajar) {
				wait();
			}
		}

		numIphone--;
		System.out.println("Iphone " + id + " baja");
		if (numAndroid + numIphone == 0) { // se han bajado todos
			puedeSubir = true;
			puedeBajar = false;
			notifyAll();
			System.out.println("Todos abajo. Barca vacia");
		}
	}

	public boolean esSegura(int android, int iphone) {
		if ((android == 3 && iphone == 1) || (android == 1 && iphone == 3)) {
			return false;
		}
		return true;
	}

}