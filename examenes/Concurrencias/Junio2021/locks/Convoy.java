package examenes.Concurrencias.Junio2021.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Convoy {
	private int tam;
	private Lock l;
	private int nFurgonetas;
	private int lider;

	// condiciones de sincronizacion
	private Condition cIniciarRuta;
	private boolean iniciarRuta;

	private Condition cLlegarDestino;
	private boolean llegarDestino;

	private Condition cLiderAbandona;
	private boolean liderAbandona;

	public Convoy(int tam) {
		this.tam = tam;
		l = new ReentrantLock();
		cIniciarRuta = l.newCondition();
		cLlegarDestino = l.newCondition();
		cLiderAbandona = l.newCondition();
		iniciarRuta = false;
		llegarDestino = false;
		liderAbandona = false;
		nFurgonetas = 0;
		lider = -1;
	}

	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras
	 * 
	 * @throws InterruptedException
	 **/
	public int unir(int id) throws InterruptedException {
		l.lock();
		try {
			nFurgonetas++;
			if (nFurgonetas == 1) {
				System.out.println("** Furgoneta " + id + " lidera el convoy **");
				lider = id;
			} else {
				System.out.println("Furgoneta " + id + " seguidora");
			}

			if (nFurgonetas == tam) {
				iniciarRuta = true;
				cIniciarRuta.signalAll();
			}

			while (!iniciarRuta) {
				cIniciarRuta.await();
			}
			return lider;
		} finally {
			l.unlock();
		}
	}

	/**
	 * La furgoneta lider espera a que todas las furgonetas se unan al convoy
	 * Cuando esto ocurre calcula la ruta y se pone en marcha
	 * 
	 * @throws InterruptedException
	 */
	public void calcularRuta(int id) throws InterruptedException {
		l.lock();
		try {
			while (!iniciarRuta) {
				cIniciarRuta.await();
			}
			System.out.println("** Furgoneta " + id + " lider:  ruta calculada, nos ponemos en marcha **");
		} finally {
			l.unlock();
		}
	}

	/**
	 * La furgoneta lider avisa al las furgonetas seguidoras que han llegado al
	 * destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public void destino(int id) throws InterruptedException {
		l.lock();
		try {
			llegarDestino = true;
			System.out.println("** Furgoneta " + id + " lider: hemos llegado al destino **");
			cLiderAbandona.signalAll();

			while (!liderAbandona) {
				cLiderAbandona.await();
			}
			System.out.println("** Furgoneta " + id + " lider abandona el convoy **");

		} finally {
			l.unlock();
		}
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al
	 * destino
	 * y abandonan el convoy
	 * 
	 * @throws InterruptedException
	 **/
	public void seguirLider(int id) throws InterruptedException {
		l.lock();
		try {
			while (!llegarDestino) {
				cLlegarDestino.await();
			}
			nFurgonetas--;
			System.out.println("Furgoneta " + id + " abandona el convoy");

			if (nFurgonetas == 1) {
				liderAbandona = true;
				cLiderAbandona.signalAll();
			}

		} finally {
			l.unlock();
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
