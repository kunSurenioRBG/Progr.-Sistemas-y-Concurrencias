package paseo_2v;


import java.util.concurrent.Semaphore;

public class Barca {
	private int N;

	private int nMenores;
	private int nAdultos;

	private Semaphore menor;
	private int nPasajeros;
	private Semaphore esperaLleno;
	private Semaphore adulto;
	private int menores;
	private int mayores;

	private Semaphore siguienteMenor;
	private Semaphore siguienteMayor;
	
	
	
	public Barca(int N,int menoresEnBarca) {
		this.N = N;
		this.nMenores=menoresEnBarca;
		nAdultos = N - nMenores;
		esperaLleno = new Semaphore(0);
		menor = new Semaphore(1);
		nPasajeros = 0;
		esperaLleno = new Semaphore(0);
		adulto = new Semaphore(1);
		menores = 0;
		mayores = 0;
		siguienteMenor = new Semaphore(1);
		siguienteMayor = new Semaphore(1);
	}
	/*
	 *
	 * Nuevo para la segunda versión:
	 * El menor id no puede subirse a la Barca hasta que haya sitio para él,
	 * y se hayan bajado los pasajeros del paseo anterior (si no es el primer paseo)
	 * 
	 * El menor id se sube a la Barca. Si es el último pasajero avisa al Barquero
	 * de que la Barca está completa.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la Barca
	 * 
	 * 
	 * 
	 */
	public void  subeMenor(int id) throws InterruptedException {
		menor.acquire();
		nPasajeros++;
		menores++;
		if (nPasajeros < N) {
			System.out.println("El menor "+id+" se ha subido a la barca");
			menor.release();
			siguienteMenor.release();
		} else {
			esperaLleno.release();
			siguienteMenor.acquire();
			nMenores++;
			System.out.println("El menor "+id+" se ha bajado de la barca");
		}
	}

	/*
	 * 
	 * Nuevo para la segunda versión:
	 * El Adulto id no puede subirse a la Barca hasta que haya sitio para él y
	 *  -se han bajado los pasajeros del paseo anterior (si no es el primer paseo)
	 *  -se han subido los numMenor menores a la Barca
	 * 
	 * El adulto id se sube a la Barca. Si es el último pasajero avisa al Barquero
	 * de que la Barca está completa.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la Barca
	 */
	public void subeAdulto(int id) throws InterruptedException {
		adulto.acquire();
		nPasajeros++;
		mayores++;
		if (nPasajeros < N) {
			System.out.println("El adulto "+id+" se ha subido a la barca");
			adulto.release();
			siguienteMayor.release();
		} else {
			System.out.println("El adulto "+id+" se ha bajado de la barca");
			esperaLleno.release();
			siguienteMayor.acquire();
			nAdultos++;
		}
	}
	/*
	 * El barquero espera hasta que la Barca tenga N pasajeros para
	 * comenzar el viaje
	 */
	public void esperoLleno() throws InterruptedException {
		esperaLleno.acquire();
		System.out.println("Comienza el viaje!!!");
	}
	
	/*
	 * Cuando termina el viaje avisa a los pasajeros que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		siguienteMenor.release();
		siguienteMayor.release();
		esperaLleno.release();
		System.out.println("Fin del viaje!!!");
		if (nPasajeros > 0 && menores > 0) {
			nPasajeros--;
			menores--;
			menor.release();
		}
		if (menores == 0 && mayores > 0) {
			nPasajeros --;
			mayores--;
			adulto.release();
		}
	}
}

//CS-2v-Menor: Tiene que esperar si no hay sitio libre, o si aún no se han bajado los pasajeros del paseo anterior (si no es el primer paseo).
//CS-2v-Adulto: No puede subirse a la Barca hasta que no se han subido los Menores.

