package practicas.practica6.fumadores;

import java.util.concurrent.*;


public class Mesa {

	public Semaphore sAgente = new Semaphore(1);
	private Semaphore[] sFumador = new Semaphore[3];

	public Mesa(){
		for (int i = 0; i < sFumador.length; i++) {
			sFumador[i] = new Semaphore(0);
		}
	}
	
	
	public void qFumar(int id) throws InterruptedException {
		sFumador[id].acquire();
		System.out.println("Fumador "+id+" coge los ingredientes");
		
	}
	public void finFumar(int id) {
		System.out.println("Fumador "+id+" ha terminado de fumar");

		sAgente.release();
		
	}
	public void nuevosIng(int ing) throws InterruptedException { // se pasa el ingrediente que no se pone

		sAgente.acquire();
		
		System.out.print("El agente ha puesto los ingredientes ");
		if (ing==0) System.out.println("1 y 2");
		else if (ing == 1) System.out.println("0 y 2");
		else System.out.println("0 y 1");

		sFumador[ing].release();
		
	}

}

//CS-Fumador i: No puede fumar hasta que el fumador anterior no ha terminado
// de fumar y sus ingredientes están sobre la mesa
//CS-Agente: no puede poner nuevos ingredientes hasta que el fumador anterior 
//no ha terminado de fumar
