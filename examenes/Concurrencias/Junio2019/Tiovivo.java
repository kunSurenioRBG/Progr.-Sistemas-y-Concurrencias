import java.util.concurrent.Semaphore;

public class Tiovivo {
	private Semaphore mutex = new Semaphore(1);
	private Semaphore estaParado = new Semaphore(1);
	private int pasajeros = 0;
	private Semaphore estaLleno = new Semaphore(0);
	private Semaphore finViaje = new Semaphore(0);

	public Tiovivo(int i) {
		this.pasajeros = i;
	}

	public void subir(int id) throws InterruptedException {
		estaParado.acquire();
		mutex.acquire();

		pasajeros++;
		if (pasajeros == 5) {
			System.out.println("Pasajero " + id + " acaba de subirse");
			estaLleno.release();
		} else {
			estaParado.release();
		}
		mutex.release();
	}

	public void bajar(int id) throws InterruptedException {
		finViaje.acquire();
		mutex.acquire();

		pasajeros--;
		if (pasajeros > 0) {
			finViaje.release(); // un pasajero sabe que tiene que bajarse cuando acaba un viaje
		} else if (pasajeros == 0) {
			estaParado.release(); // nuevos pasajeros empezaran a subirse
		}
		mutex.release();
	}

	public void esperaLleno() throws InterruptedException {
		estaLleno.acquire();
		System.out.println("Empieza el viaje");
	}

	public void finViaje() {
		System.out.println("Fin del viaje");
		finViaje.release();
	}
}
