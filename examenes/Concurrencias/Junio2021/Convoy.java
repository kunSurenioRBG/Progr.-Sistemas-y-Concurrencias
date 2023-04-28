import java.util.concurrent.Semaphore;

public class Convoy {

	private int tam;
	private Semaphore mutex;
	private int furgonetas;
	private int idFurgonetaLider;
	private Semaphore arranqueComboy;
	private Semaphore viajandoComboy;
	private Semaphore furgonetasAbandonando;

	public Convoy(int tam) {
		this.tam = tam;
		mutex = new Semaphore(1); // los camiones
		furgonetas = 0;
		idFurgonetaLider = 0;
		arranqueComboy = new Semaphore(0);
		viajandoComboy = new Semaphore(1);
		furgonetasAbandonando = new Semaphore(0);
	}

	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras
	 **/
	public int unir(int id) throws InterruptedException {
		mutex.acquire();
		furgonetas++;

		if (furgonetas == 1) {
			System.out.println("Furgoneta " + id + " lidera el comboy");
			idFurgonetaLider = id;
		} else {
			System.out.println("Furgoneta " + id + " sigue al lider");
		}

		if (furgonetas == tam) {
			arranqueComboy.release();
		}
		mutex.release();

		return idFurgonetaLider;
	}

	/**
	 * La furgoneta lider espera a que todas las furgonetas se unan al convoy
	 * Cuando esto ocurre calcula la ruta y se pone en marcha
	 */
	public void calcularRuta(int id) throws InterruptedException {
		arranqueComboy.acquire();
		System.out.println("** Furgoneta " + id + " lider:  ruta calculada, nos ponemos en marcha **");

	}

	/**
	 * La furgoneta lider avisa a las furgonetas seguidoras que han llegado al
	 * destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 **/
	public void destino(int id) throws InterruptedException {
		System.out.println("** Furgoneta " + id + " lider: hemos llegado al destino **");
		viajandoComboy.release();
		furgonetasAbandonando.acquire();
		System.out.println("** Furgoneta " + id + " lider abandona el convoy **");
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al
	 * destino
	 * y abandonan el convoy
	 **/
	public void seguirLider(int id) throws InterruptedException {
		viajandoComboy.acquire();
		mutex.acquire();

		furgonetas--;
		System.out.println("Furgoneta " + id + " abandona el convoy");

		if (furgonetas > 1) {
			viajandoComboy.release();
		} else {
			furgonetasAbandonando.release();
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
