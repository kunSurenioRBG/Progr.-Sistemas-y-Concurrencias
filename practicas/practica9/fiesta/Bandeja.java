package practicas.practica9.fiesta;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {

	private Lock l = new ReentrantLock(true);
	private int R = 8; // numero de raciones por pastel
	private int rac = 0; // numero de raciones

	private Condition duerme = l.newCondition();
	private boolean llamado = false;
	private Condition esperarPastelero = l.newCondition();
	private boolean ninioAvisado = false;
	private boolean quedaTarta = false;

	/**
	 * Un niño que quiere una ración de tarta llama a este método para
	 * cogerla de la bandeja.
	 * El niño debe esperar si la bandeja está vacía a que el pastelero
	 * ponga una nueva tarta
	 * 
	 * @param id del niño que pide la ración
	 * @throws InterruptedException
	 */

	public void quieroRacion(int id) throws InterruptedException {
		l.lock();
		try {
			if (rac == 0) {
				llamado = true; // el ninio llama al pastelero, solicitando una nueva tarta
				duerme.signal(); // pastelero se despierta
				while (!ninioAvisado) {
					esperarPastelero.await();
				}
				ninioAvisado = false;
				quedaTarta = true;
			}
			if (quedaTarta) {
				System.out.println("Ninio " + id + " coge trozo de tarta. Quedan " + rac + " raciones");
				rac--;
			}

		} finally {
			l.unlock();
		}
	}

	/**
	 * El pastelero llama a este método para poner una nueva tarta en
	 * la bandeja. Tiene que esperar a que la bandeja esté vacía para ponerla.
	 * 
	 * @throws InterruptedException
	 */

	public void tarta() throws InterruptedException {
		l.lock();
		try {
			while (!llamado) {
				duerme.await(); // el pastelero sigue durmiendo mientras ningun ninio notifique que falta tarta
			}
			System.out.println("No queda mas tarta. Solicitando una nueva al vago del repostero.");
			llamado = false;
			Thread.sleep(400); // tiempo que tarda el pastelero en hacer la tarta
			rac = R;
			esperarPastelero.signal(); // el ninio se pone a esperar al pastelero
			ninioAvisado = true;
			quedaTarta = false;
		} finally {
			l.unlock();
		}

	}

}
