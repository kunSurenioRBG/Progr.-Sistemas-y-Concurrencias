package examenes.Concurrencias.Rehechos.Semaforos.Junio2021;

import java.util.concurrent.Semaphore;

public class Convoy {
	private int tam;
	private int nConvoy;
	private int lider;

	private Semaphore mutex;
	private Semaphore convoyEnMarcha;
	private Semaphore convoyHaLlegado;
	private Semaphore liderAbandona;

	public Convoy(int tam) {
		this.tam = tam;
		nConvoy = 0;
		lider = -1;

		mutex = new Semaphore(1);
		convoyEnMarcha = new Semaphore(0);
		convoyHaLlegado = new Semaphore(0);
		liderAbandona = new Semaphore(0);
	}

	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras
	 * 
	 * @throws InterruptedException
	 **/
	public int unir(int id) throws InterruptedException {
		mutex.acquire();
		nConvoy++;
		if (nConvoy == 1) {
			lider = id;
			System.out.println("** Furgoneta " + id + " lidera del convoy **");
		} else {
			System.out.println("Furgoneta " + id + " seguidora");
		}

		if (nConvoy == tam) {
			convoyEnMarcha.release();
		}
		mutex.release();
		return lider;
	}

	/**
	 * La furgoneta lider espera a que todas las furgonetas se unan al convoy
	 * Cuando esto ocurre calcula la ruta y se pone en marcha
	 * 
	 * @throws InterruptedException
	 */
	public void calcularRuta(int id) throws InterruptedException {
		convoyEnMarcha.acquire();
		System.out.println("** Furgoneta " + id + " lider:  ruta calculada, nos ponemos en marcha **");
	}

	/**
	 * La furgoneta lider avisa al las furgonetas seguidoras que han llegado al
	 * destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public void destino(int id) throws InterruptedException {
		System.out.println("** Furgoneta " + id + " lider:  hemos llegado al destino **");
		convoyHaLlegado.release();

		// despues de que todas las furgonetas abandonen, abandonara el lider
		liderAbandona.acquire();
		System.out.println("** Furgoneta " + id + " lider abandona el convoy **");
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al
	 * destino
	 * y abandonan el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public void seguirLider(int id) throws InterruptedException {
		convoyHaLlegado.acquire();
		mutex.acquire();
		System.out.println("Furgoneta " + id + " abandona el convoy");
		nConvoy--;

		if (nConvoy > 1) {
			convoyHaLlegado.release();
		} else {
			liderAbandona.release();
		}
		mutex.release();
	}

	/**
	 * Programa principal. No modificar
	 **/
	public static void main(String[] args) {
		final int NUM_FURGO = 10;
		Convoy c = new Convoy(NUM_FURGO);
		Furgoneta flota[] = new Furgoneta[NUM_FURGO];

		for (int i = 0; i < NUM_FURGO; i++)
			flota[i] = new Furgoneta(i, c);

		for (int i = 0; i < NUM_FURGO; i++)
			flota[i].start();
	}

}
