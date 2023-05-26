package paseo_2v;


public class Barca {
	private int N;
	private int maxMenor;
	
	
	
	public Barca(int N,int menoresEnBarca) {
		this.N = N;
		this.maxMenor=menoresEnBarca;
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
	public void  subeMenor(int id)  {
		
		//TODO
		System.out.println("El menor "+id+" se ha subido a la barca ");
		//TODO
		System.out.println("El menor "+id+" se ha bajado de la barca");
		//TODO
		
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
	public void subeAdulto(int id)  {
		//TODO
		System.out.println("El adulto "+id+" se ha subido a la barca");
		//TODO
		System.out.println("El adulto "+id+" se ha bajado de la barca");
		//TODO
	}
	/*
	 * El barquero espera hasta que la Barca tenga N pasajeros para
	 * comenzar el viaje
	 */
	public void esperoLleno()  {
		//TODO
		System.out.println("Comienza el viaje!!!");
		//TODO
	}
	
	/*
	 * Cuando termina el viaje avisa a los pasajeros que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() {
		//TODO
		System.out.println("Fin del viaje!!!");
		//TODO
		
	}
}

//CS-2v-Menor: Tiene que esperar si no hay sitio libre, o si aún no se han bajado los pasajeros del paseo anterior (si no es el primer paseo).
//CS-2v-Adulto: No puede subirse a la Barca hasta que no se han subido los Menores.

