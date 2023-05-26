package paseo_1v;


public class Barca {
	private int N;

	
	public Barca(int N) {
		this.N = N; //capacidad de la Barca
	}
	
	/*
	 * El menor id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión,
	 * un menor que quiera subir podrá hacerlo sin tener que esperar.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la barca
	 */
	public void  subeMenor(int id) {
		//TODO
		System.out.println("El menor "+id+" se ha subido a la barca");
		//TODO
		
		System.out.println("El menor "+id+" se ha bajado de la barca");
		//TODO
	}

	/*
	 * El adulto id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión, un adulto que 
 	 * quiera subir podrá hacerlo sin tener que esperar. 
	 * A continuación, espera hasta que finalice el paseo.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeAdulto(int id) {
		//TODO
		System.out.println("El adulto "+id+" se ha subido a la barca");
		//TODO
		System.out.println("El adulto "+id+" se ha bajado de la barca");
		//TODO
	}
	/*
	 * El barquero espera hasta que la barca tenga N pasajeros para
	 * comenzar el paseo
	 */
	public void esperoLleno() {
		//TODO
		System.out.println("Comienza el viaje!!!");
	}
	/*
	 * Cuando termina el paseo, el barquero avisa a los excusionistas  que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		
		//TODO
		System.out.println("Fin del viaje!!!");
		//TODO
	}
}

//CS-Barquero: El barquero no pone en marcha la barca hasta que no se han subido N excursionistas
//CS-Menor: Un menor que está en la barca no puede bajarse hasta que no se ha terminado el paseo.
//CS-Adulto: Un adulto que está en la barca no puede bajarse hasta que no se ha terminado el paseo 
//y no se hayan bajado todos los menores (en caso de que se hubiera subido alguno).
