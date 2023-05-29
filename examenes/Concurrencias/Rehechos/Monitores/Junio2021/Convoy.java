package examenes.Concurrencias.Rehechos.Monitores.Junio2021;

public class Convoy {
	private int nConvoy;
	private int tam;
	private int lider;

	private boolean convoyEnMarcha = false;
	private boolean convoyHaLlegado = false;
	private boolean liderAbandona = false;

	public Convoy(int tam) {
		nConvoy = 0;
		this.tam = tam;
		lider = -1;
	}

	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras
	 * 
	 * @throws InterruptedException
	 **/
	public synchronized int unir(int id) throws InterruptedException {
		nConvoy++;
		if (nConvoy == 1) {
			lider = id;
			System.out.println("** Furgoneta " + id + " lidera del convoy **");
		} else {
			System.out.println("Furgoneta " + id + " seguidora");
		}

		if (nConvoy == tam) {
			convoyEnMarcha = true;
			notifyAll();
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
		while (!convoyEnMarcha) {
			wait();
		}
		System.out.println("** Furgoneta " + id + " lider:  ruta calculada, nos ponemos en marcha **");
	}

	/**
	 * La furgoneta lider avisa al las furgonetas seguidoras que han llegado al
	 * destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 * @throws InterruptedException
	 **/
	public synchronized void destino(int id) throws InterruptedException {
		System.out.println("** Furgoneta " + id + " lider:  hemos llegado **");
		convoyHaLlegado = true;
		notifyAll();
		
		while (!liderAbandona) {
			wait();
		}
		System.out.println("** Furgoneta " + id + " lider abandona el convoy **");
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al
	 * destino
	 * y abandonan el convoy
	 * @throws InterruptedException
	 **/
	public synchronized void seguirLider(int id) throws InterruptedException {
		while (!convoyHaLlegado) {
			wait();
		}
		System.out.println("Furgoneta " + id + " abandona el convoy");
		nConvoy--;

		if (nConvoy > 1) {
			convoyHaLlegado = true;
			notifyAll();
		} else {
			liderAbandona = true;
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
