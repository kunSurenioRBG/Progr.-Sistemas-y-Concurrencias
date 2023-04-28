import java.util.concurrent.*;

public class GestorAgua {
	private final int HIDROGENO = 2;
	private final int OXIGENO = 1;

	private Semaphore mutex;
	private Semaphore hidrogeno;
	private Semaphore oxigeno;
	private int moleculasHidrogeno;
	private int moleculasOxigeno;
	private Semaphore formaAgua;

	public GestorAgua() {
		mutex = new Semaphore(1);
		hidrogeno = new Semaphore(1);
		oxigeno = new Semaphore(1);
		moleculasHidrogeno = 0;
		moleculasOxigeno = 0;
		formaAgua = new Semaphore(0);

	}

	public void hListo(int id) throws InterruptedException {
		hidrogeno.acquire();
		mutex.acquire();

		moleculasHidrogeno++;
		if (moleculasOxigeno == OXIGENO && moleculasHidrogeno == HIDROGENO) {
			formaAgua.release();
		} else if (moleculasHidrogeno < HIDROGENO) {
			hidrogeno.release();
		}
		mutex.release();

		formaAgua.acquire();
		mutex.acquire();

		moleculasHidrogeno--;

		if (moleculasHidrogeno + moleculasOxigeno == 0) {
			hidrogeno.release();
			oxigeno.release();
		} else {
			formaAgua.release();
		}
		mutex.release();
	}

	public void oListo(int id) throws InterruptedException {
		oxigeno.acquire();
		mutex.acquire();

		moleculasOxigeno++;
		if (moleculasOxigeno == OXIGENO && moleculasHidrogeno == HIDROGENO) {
			formaAgua.release();
		}
		mutex.release();

		formaAgua.acquire();
		mutex.acquire();

		moleculasOxigeno--;

		if (moleculasHidrogeno + moleculasOxigeno == 0) {
			hidrogeno.release();
			oxigeno.release();
		} else {
			formaAgua.release();
		}
		mutex.release();

	}
}