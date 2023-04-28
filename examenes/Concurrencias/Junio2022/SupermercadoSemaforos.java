package Junio2022;

import java.util.concurrent.Semaphore;

public class SupermercadoSemaforos implements Supermercado {

	private Cajero permanente;
	private Cajero ocasional;

	private Semaphore mutex = new Semaphore(1);
	private Semaphore cierraTienda = new Semaphore(0);
	private boolean cerrado;
	private int clientes;
	private int cajeros;
	private Semaphore cajeroPermanente = new Semaphore(0);
	private Semaphore clienteAtendido = new Semaphore(0);

	private boolean vaCerrar;

	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); // crea el primer cajero, el permanente
		permanente.start();
		cerrado = false;
		clientes = 0;
		cajeros = 1;
		vaCerrar = false;
	}

	@Override
	public void fin() throws InterruptedException {
		System.out.println("FIN: Cerrando el supermercado");
		cierraTienda.acquire();
		cerrado = true;
		System.out.println("FIN: Supermercado cerrado");
	}

	@Override
	public void nuevoCliente(int id) throws InterruptedException {
		if (cerrado) {
			System.exit(-1);
		}
		mutex.acquire();
		System.out.println("CLIENTE: El cliente con id " + id + " entra al supermercado.");
		clientes++;
		mutex.release();

		if (clientes == 1) {
			cajeroPermanente.release();
			cierraTienda.release();
			System.out.println("CLIENTE: El cliente con id " + id + " ha despertao al cajero permantente.");
		}

		if (clientes > 3 * cajeros) {
			cajeros++;
			ocasional = new Cajero(this, false);
			ocasional.start();
			System.out.println("CLIENTE: Se crea un nuevo cajero ocasional.");
		}

		System.out.println("CLIENTE: El cliente con id " + id + " espera en la cola.");
		clienteAtendido.acquire();
		System.out.println("CLIENTE: EL cliente con id " + id + " ha terminado.");

	}

	@Override
	public boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		cajeroPermanente.acquire();
		if (clientes == 0) {
			System.out.println("Supermercado cerrado.");
			vaCerrar = false;
		}

		if (clientes > 0) {
			cajeroPermanente.release();
			vaCerrar = true;
		}

		mutex.acquire();
		clientes--;
		mutex.release();
		System.out.println("El cliente con id " + id + " ha sido atendido.");
		clienteAtendido.release();

		return vaCerrar;
	}

	@Override
	public boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		mutex.acquire();
		clientes--;
		mutex.release();

		if (clientes < 3 * cajeros) {
			cajeros--;
			System.out
					.println("OCASIONAL: El cajero atiende al cliente " + id + " y cierra el cajero " + cajeros + ".");
			clienteAtendido.release();
			return false;
		}

		System.out.println("OCASIONAL: El cajero atiende al cliente " + id);
		clienteAtendido.release();
		return true;
	}

}
