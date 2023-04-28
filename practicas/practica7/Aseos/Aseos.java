package Aseos;

import java.util.concurrent.Semaphore;

public class Aseos {
	private int clientes = 0;
	private Semaphore mutexC;
	private Semaphore mutexC2;
	private Semaphore equipoLimpieza;
	private Semaphore quiereLimpiar;
	
	public Aseos(){
		equipoLimpieza = new Semaphore(1); // equipo de limpieza (solo 1)
		mutexC = new Semaphore(1); // clientes (varios)
		mutexC2 = new Semaphore(1);
		quiereLimpiar = new Semaphore(1);
	}
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		mutexC2.acquire();
		quiereLimpiar.acquire();
		mutexC.acquire();
		if (clientes == 0) {
			equipoLimpieza.acquire();
		}
		clientes++;
		System.out.println("Cliente "+id+" entra.");
		mutexC.release();
		quiereLimpiar.release();
		mutexC2.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		mutexC.acquire();
		clientes--;
		System.out.println("Sale cliente "+id+" del aseo.");
		if (clientes == 0) {
			equipoLimpieza.release();
		}
		mutexC.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		// el equipo quiere limpiar
		System.out.println("****EQUIPO QUIERE ENTRAR****");
		quiereLimpiar.acquire();
		equipoLimpieza.acquire();
		System.out.println("****EQUIPO ENTRA****");
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza(){
		System.out.println("****EQUIPO QUIERE SALIR****");
    	equipoLimpieza.release();
		quiereLimpiar.release();
		
	}
}
