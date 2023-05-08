package practicas.practica9.parejas;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {

	private Lock l = new ReentrantLock(true);
	private boolean hayHombre = false;
	private Condition cHayHombre = l.newCondition();

	private boolean hayMujer = false;
	private Condition cHayMujer = l.newCondition();

	private boolean hayPareja = false;
	private Condition cHayPareja = l.newCondition();

	private int n = 0;

	/**
	 * un hombre llega a la sala para formar una pareja
	 * si ya hay otra mujer en la sala o si aún no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public void llegaHombre(int id) throws InterruptedException {
		l.lock();
		try {
			while (hayHombre) {
				cHayHombre.await(); // mientras haya un hombre dentro de la sala, el hombre tiene que esperar
			}
			System.out.println("Llega hombre " + id);
			n++;
			hayHombre = true;

			if (n < 2) {
				while (!hayPareja) {
					cHayPareja.await();
				}
			} else {
				hayPareja = true;
				cHayPareja.signal();
				System.out.println("Ya hay una pareja :)");
			}
			n--; // una pareja a tomar por culo :3

			if (n == 0) {
				hayPareja = false;
				hayHombre = false;
				cHayHombre.signal();
				hayMujer = false;
				cHayMujer.signal();

			}
		} finally {
			l.unlock();
		}

	}

	/**
	 * una mujer llega a la sala para formar una pareja
	 * debe esperar si ya hay otra mujer en la sala o si aún no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public void llegaMujer(int id) throws InterruptedException {
		l.lock();
		try {
			while (hayMujer) {
				cHayMujer.await(); // mientras haya un hombre dentro de la sala, el hombre tiene que esperar
			}
			System.out.println("Llega mujer " + id);
			n++;
			hayMujer = true;

			if (n < 2) {
				while (!hayPareja) {
					cHayPareja.await();
				}
			} else {
				hayPareja = true;
				cHayPareja.signal();
				System.out.println("Ya hay una pareja :)");
			}
			n--; // una pareja a tomar por culo :3

			if (n == 0) {
				hayPareja = false;
				hayHombre = false;
				cHayHombre.signal();
				hayMujer = false;
				cHayMujer.signal();

			}
		} finally {
			l.unlock();
		}
	}
}
