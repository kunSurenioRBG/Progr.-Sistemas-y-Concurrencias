package examenes.Concurrencias.Rehechos.Semaforos.Junio2022;

import java.util.concurrent.Semaphore;

public class SupermercadoSemaforos implements Supermercado {
	private int nClientes;
	private boolean cerrado;
	private int nCajeros;
	private boolean estaCerrando; // boolean para los cajeros

	private Semaphore mutex;
	private Semaphore cajeroPermanente;
	private Semaphore clienteAtendido;
	private Semaphore cierraTienda;

	private Cajero permanente;
	private Cajero ocasional;

	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); // crea el primer cajero, el permanente
		permanente.start();
		
		mutex = new Semaphore(1);
		cajeroPermanente = new Semaphore(0);
		clienteAtendido = new Semaphore(0);
		cierraTienda = new Semaphore(0);
		
		nClientes = 0;
		cerrado = false;
		nCajeros = 1;
		estaCerrando = false;
	}

	@Override
	public void fin() throws InterruptedException {
		System.out.println("Cerrando tienda.");
		cierraTienda.acquire();
		estaCerrando = true;
		System.out.println("La tienda ha cerrado.");
	}

	@Override
	public void nuevoCliente(int id) throws InterruptedException {
		if (cerrado) {
			System.exit(-1);
		}
		mutex.acquire();
		nClientes++;
		System.out.println("El cliente " + id + " ha entrado a la tienda. Numero de clientes: " + nClientes);
		mutex.release();

		if (nClientes == 1) {
			cajeroPermanente.release();
			// como solo queda 1, despues de "permanenteAtiendeCliente", la tienda cerrara
			cierraTienda.release();
			System.out.println("El cajero permanente se ha despertado.");
		}

		if (nClientes > 3 * nCajeros) {
			ocasional = new Cajero(this, false);
			ocasional.start();
			nCajeros++;
			System.out.println("Viene un nuevo cajero ocasional.");
		}

		System.out.println("EL cliente " + id + " esta esperando en la cola.");
		clienteAtendido.acquire();

	}

	@Override
	public boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		cajeroPermanente.acquire();
		if (nClientes == 0) {
			estaCerrando = false;
			System.out.println("El supermercado esta cerrado.");
		}

		if (nClientes > 0) {
			cajeroPermanente.release();
			estaCerrando = true;
		}

		mutex.acquire();
		nClientes--;
		mutex.release();
		System.out.println("PERMANENTE: El cliente con id " + id + " ha sido atendido. Quedan: " + nClientes);
		clienteAtendido.release();
		return estaCerrando;
	}

	@Override
	public boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		mutex.acquire();
		nClientes--;
		mutex.release();

		if (nClientes < 3 * nCajeros) {
			nCajeros--;
			System.out.println("Un cajero ocasional se larga.");
			clienteAtendido.release();
			return false;
		}
		System.out.println("OCASIONAL: El cliente con id " + id + " ha sido atendido. Quedan: " + nClientes);
		clienteAtendido.release();
		return true;
	}

}
