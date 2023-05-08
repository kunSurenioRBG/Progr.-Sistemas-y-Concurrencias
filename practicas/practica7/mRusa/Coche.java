package practicas.practica7.mRusa;

import java.util.concurrent.*;

public class Coche extends Thread {
	private int C;
	private int enCoche;
	private Semaphore puedeSubir, puedeBajar, esperaLleno;

	public Coche(int cap) {
		C = cap;
		enCoche = 0;
		puedeSubir = new Semaphore(1);
		puedeBajar = new Semaphore(0);
		esperaLleno = new Semaphore(0);
	}

	public void nuevoViaje(int id) throws InterruptedException {
		puedeSubir.acquire();
		enCoche++;
		if (enCoche == C) {
			esperaLleno.release();
		}

		if (enCoche == 0) {
			puedeSubir.release();
		}
	}

	public void esperaLleno() throws InterruptedException {
		esperaLleno.acquire();
		System.out.println("***EMPIEZA VIAJE***");
	}

	public void finViaje() {
		System.out.println("***FIN VIAJE***");
		puedeBajar.release();
	}

	public void run() {
		while (true) {

			try {
				this.esperaLleno();
				Thread.sleep(200);
				this.finViaje();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
