package examenes.Concurrencias.Septiembre2021_monitores;

public class DatoCompartido {
	private int dato; //Dato a procesar
	private int nProcesadores; //Numero de procesadores totales
	private int procPend; //Numero de procesadores pendientes de procesar el dato

	private boolean puedeLeer, puedeGenerar, puedeDevolver, esperaProcesadores;
	
	
	/* Recibe como par�metro el n�mero de procesadores que tienen que manipular 
	 * cada dato generado. Debe ser un n�mero mayor que 0. 
	 */
	public DatoCompartido(int nProcesadores) {
		this.nProcesadores = nProcesadores;
		procPend = nProcesadores;
		puedeLeer = false;
		esperaProcesadores = true;
		puedeDevolver = false;
		puedeGenerar = true;
	}
	
	/*  El Generador utiliza este metodo para almacenar un nuevo dato a procesar. 
	 *  Una vez almacenado el dato se debe avisar a los procesadores de que se ha 
	 *  almacenado un nuevo dato. 
	 *  
	 *  Por ultimo, el Generador tendra que esperar en este metodo a que todos los 
	 *  procesadores terminen de procesar el dato. Una vez que todos terminen, 
	 *  se devolvera el resultado del procesamiento, permitiendo al Generador 
	 *  la generacion de un nuevo dato.
	 * 
	 *  CS_Generador: Una vez generado un dato, el Generador espera a que todos los procesadores 
	 *  terminen antes de generar el siguiente dato
	 */
	public synchronized int generaDato(int d) throws InterruptedException {
		//COMPLETAR y colocar los mensajes en el lugar apropiado dentro del c�digo
		while (!puedeGenerar){
			wait();
		}

		dato = d;
		puedeDevolver = false;
		System.out.println("Dato a procesar: " + dato);
		System.out.println("Numero de procesadores pendientes: " + procPend);

		puedeLeer = true;
		notifyAll();

		while(!puedeDevolver){
			wait();
		}
		
		return dato;
	}

	/*  El Procesador con identificador id utiliza este metodo para leer el 
	 *  dato que debe procesar (el dato se devuelve como valor de retorno del metodo). 
	 *  Debera esperarse si no hay datos nuevos para procesar 
	 *  o si otro procesador esta manipulando el dato. 
	 * 
	 *  CS1_Procesador: Espera si no hay un nuevo dato que procesar. 
	 *                  Esto puede ocurrir porque el generador aun no haya almacenado 
	 *                  ningun dato o porque el Procesador ya haya procesado el dato 
	 *                  almacenado en ese momento en el recurso compartido.
	 *  CS2_Procesador: Espera a que el dato este disponible para poder procesarlo 
	 *                  (es decir, no hay otro Procesador procesando al dato)
	 */
	public synchronized int leeDato(int id) throws InterruptedException {
		while (!puedeLeer){
			wait();
		}

		puedeLeer = false;
		
		return dato;
	}
	
	/*  El Procesador con identificador id almacena en el recurso compartido el resultado 
	 *  de haber procesado el dato. Una vez hecho esto actuara de una de las dos formas siguientes: 
	 *  
	 *  (1) Si aun hay procesadores esperando a procesar el dato los avisara, 
	 *  (2) Si el era el ultimo procesador avisara al Generador de que han terminado. 
	 * 
	 */
	public synchronized void actualizaDato(int id, int datoActualizado) throws InterruptedException {
		//COMPLETAR y colocar los mensajes en el lugar apropiado dentro del c�digo
		dato = datoActualizado;
		System.out.println("	Procesador " + id + " ha procesado el dato. Nuevo dato: " + dato);

		procPend--;
		System.out.println("Numero de procesadores pendientes: " + procPend);

		if(procPend > 0){
			puedeLeer = true;
		}else{
			esperaProcesadores = false;
		}
		notifyAll();

		while(esperaProcesadores){
			wait();
		}

		procPend++;

		if(nProcesadores == procPend){
			esperaProcesadores = true;
			puedeDevolver = true;
			notifyAll();
		}


	}
}
