package paseo_1v;


import java.util.concurrent.Semaphore;

public class Barca {
	private int N, nMenores, nAdultos;
	private Semaphore mutex, puedeSalirBarca, puedeBajarMenor, puedeBajarAdulto;

	
	public Barca(int N) {
		this.N = N; //capacidad de la Barca
		nMenores = 0;
		nAdultos = 0;
		mutex = new Semaphore(1);
		puedeSalirBarca = new Semaphore(0);
		puedeBajarMenor = new Semaphore(0);
		puedeBajarAdulto = new Semaphore(0);
	}
	
	/*
	 * El menor id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión,
	 * un menor que quiera subir podrá hacerlo sin tener que esperar.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeMenor(int id) throws InterruptedException {
		//SUBE MENOR
		mutex.acquire();
		nMenores++;
		System.out.println("El menor "+id+" se ha subido a la barca. Hay " + nMenores + " menores.");
		if(nMenores + nAdultos == N){
			puedeSalirBarca.release();
		}
		mutex.release();

		//BAJA MENOR
		puedeBajarMenor.acquire();
		mutex.acquire();
		nMenores--;
		System.out.println("El menor "+id+" se ha bajado de la barca. Quedan " + nMenores + " menores.");
		if(nMenores == 0){
			puedeBajarAdulto.release();
		}else{
			puedeBajarMenor.release();
		}
		mutex.release();

	}

	/*
	 * El adulto id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión, un adulto que 
 	 * quiera subir podrá hacerlo sin tener que esperar. 
	 * A continuación, espera hasta que finalice el paseo.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeAdulto(int id) throws InterruptedException {
		//SUBE ADULTO
		mutex.acquire();
		nAdultos++;
		System.out.println("El adulto "+id+" se ha subido a la barca. Hay " + nAdultos + " adultos.");
		if(nMenores + nAdultos == N){
			puedeSalirBarca.release();
		}
		mutex.release();

		//SALE ADULTO
		puedeBajarAdulto.acquire();
		mutex.acquire();
		nAdultos--;
		System.out.println("El adulto "+id+" se ha bajado de la barca. Quedan " + nAdultos + " adultos.");
		puedeBajarAdulto.release();
		mutex.release();

	}
	/*
	 * El barquero espera hasta que la barca tenga N pasajeros para
	 * comenzar el paseo
	 */
	public void esperoLleno() throws InterruptedException {
		puedeSalirBarca.acquire();
		System.out.println("Comienza el viaje!!!");
	}
	/*
	 * Cuando termina el paseo, el barquero avisa a los excusionistas  que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		System.out.println("Fin del viaje!!!");
		if(nMenores==0){
			puedeBajarAdulto.release();
		}else{
			puedeBajarMenor.release();
		}
	}
}

//CS-Barquero: El barquero no pone en marcha la barca hasta que no se han subido N excursionistas
//CS-Menor: Un menor que está en la barca no puede bajarse hasta que no se ha terminado el paseo.
//CS-Adulto: Un adulto que está en la barca no puede bajarse hasta que no se ha terminado el paseo 
//y no se hayan bajado todos los menores (en caso de que se hubiera subido alguno).
