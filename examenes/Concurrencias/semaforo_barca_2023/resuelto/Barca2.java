package paseo_2v;


import java.util.concurrent.Semaphore;

public class Barca {
	private int N;
	private int maxMenor;
	
	private int nMenores, nAdultos;
	private Semaphore puedeSalirBarca, puedeSubirMenor, puedeSubirAdulto, puedeBajarMenor, puedeBajarAdulto;
	
	public Barca(int N,int menoresEnBarca) {
		this.N = N;
		this.maxMenor=menoresEnBarca;
		nMenores = 0;
		nAdultos = 0;
		/*
		Los semaforos puedeSubirMenor, puedeSubirAdulto, puedeBajarMenor
		y puedeBajarAdulto me garantizan la exclusion mutua, por lo que
		no voy a crear un semaforo mutex
		*/
		puedeSalirBarca = new Semaphore(0);
		puedeSubirMenor = new Semaphore(1);
		puedeSubirAdulto = new Semaphore(0);
		puedeBajarMenor = new Semaphore(0);
		puedeBajarAdulto = new Semaphore(0);
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
		//SUBE MENOR
		puedeSubirMenor.acquire();
		nMenores++;
		System.out.println("El menor "+id+" se ha subido a la barca. Hay " + nMenores + " menores.");
		if(nMenores == maxMenor){
			puedeSubirAdulto.release();
		}else{
			puedeSubirMenor.release();
		}

		//BAJA MENOR
		puedeBajarMenor.acquire();
		nMenores--;
		System.out.println("El menor "+id+" se ha bajado de la barca. Quedan " + nMenores + " menores.");
		if(nMenores == 0){
			puedeBajarAdulto.release();
		}else{
			puedeBajarMenor.release();
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
		//SUBE ADULTO
		puedeSubirAdulto.acquire();
		nAdultos++;
		System.out.println("El adulto "+id+" se ha subido a la barca. Hay " + nAdultos + " adultos.");
		if(nAdultos + nMenores == N){
			puedeSalirBarca.release();
		}else{
			puedeSubirAdulto.release();
		}

		//BAJA ADULTO
		puedeBajarAdulto.acquire();
		nAdultos--;
		System.out.println("El adulto "+id+" se ha bajado de la barca. Quedan " + nAdultos + " adultos.");
		if(nAdultos == 0){
			puedeSubirMenor.release();
		}else{
			puedeBajarAdulto.release();
		}
	}
	/*
	 * El barquero espera hasta que la Barca tenga N pasajeros para
	 * comenzar el viaje
	 */
	public void esperoLleno() throws InterruptedException {
		puedeSalirBarca.acquire();
		System.out.println("Comienza el viaje!!!");
	}
	
	/*
	 * Cuando termina el viaje avisa a los pasajeros que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		System.out.println("Fin del viaje!!!");
		puedeBajarMenor.release();
		
	}
}

//CS-2v-Menor: Tiene que esperar si no hay sitio libre, o si aún no se han bajado los pasajeros del paseo anterior (si no es el primer paseo).
//CS-2v-Adulto: No puede subirse a la Barca hasta que no se han subido los Menores.

