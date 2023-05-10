package examenes.Concurrencias.Junio2021.monitores;

public class Convoy {
	private int tam;
	private int lider;
	private int nFurgonetas;

	// condiciones de sincronizacion
	private boolean iniciarRuta;
	private boolean llegarDestino;
	private boolean abandonarLider;

	public Convoy(int tam) {
		this.tam = tam;
		lider = -1;
		nFurgonetas = 0;
		iniciarRuta = false;
		llegarDestino = false;
		abandonarLider = false;
	}

	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras
	 * 
	 * @throws InterruptedException
	 **/
	public synchronized int unir(int id) throws InterruptedException {
		nFurgonetas++;
		// comprobar si soy la furgoneta lider o seguidora
		if (nFurgonetas == 1) {
			System.out.println("** Furgoneta " + id + " lidera del convoy **");
			lider = id;
		} else {
			System.out.println("Furgoneta " + id + " seguidora");
		}

		if (nFurgonetas == tam) {
			iniciarRuta = true;
			notifyAll();
		}

		while (!iniciarRuta) {
			wait();
		}

		return lider;
	}

	/**
	 * La furgoneta lider espera a que todas las furgonetas se unan al convoy
	 * Cuando esto ocurre calcula la ruta y se pone en marcha
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void calcularRuta(int id) throws InterruptedException {
		while (!iniciarRuta) {
			wait();
		}
		System.out.println("** Furgoneta " + id + " lider:  ruta calculada, nos ponemos en marcha **");
	}

	/**
	 * La furgoneta lider avisa al las furgonetas seguidoras que han llegado al
	 * destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public synchronized void destino(int id) throws InterruptedException {
		llegarDestino = true;
		System.out.println("** Furgoneta " + id + " lider: hemos llegado al destino **");
		notifyAll();

		while (!abandonarLider) {
			wait();
		}
		System.out.println("** Furgoneta " + id + " lider abandona el convoy **");
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al
	 * destino
	 * y abandonan el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public synchronized void seguirLider(int id) throws InterruptedException {
		while (!llegarDestino) {
			wait();
		}
		nFurgonetas--;
		System.out.println("Furgoneta " + id + " abandona el convoy");

		if (nFurgonetas == 1) {
			abandonarLider = true;
			notifyAll();
		}
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
