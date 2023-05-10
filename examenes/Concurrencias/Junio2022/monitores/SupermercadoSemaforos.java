package examenes.Concurrencias.Junio2022.monitores;

public class SupermercadoSemaforos implements Supermercado {

	private Cajero permanente;
	private Cajero ocasional;
	private boolean cajeroPermanenteDisponible;
	private boolean clienteEspera;
	private boolean superCerrado;
	private int nClientes;
	private int nCajerosOcasionales;
	private boolean seAtiende;

	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); // crea el primer cajero, el permanente
		cajeroPermanenteDisponible = false;
		clienteEspera = true;
		superCerrado = true;
		nCajerosOcasionales = 1;
		seAtiende = true;
	}

	@Override
	public synchronized void fin() throws InterruptedException {
		superCerrado = true;
		while (clienteEspera) {
			wait();
		}
	}

	@Override
	public synchronized void nuevoCliente(int id) throws InterruptedException {
		while (superCerrado) {
			wait();
		}
		nClientes++;

		// si tengo al menos 1 cliente, este despertara al cajero permanente
		if (nClientes == 1) {
			cajeroPermanenteDisponible = true;
			superCerrado = false;
			System.out.println("CLIENTE: El cliente con id " + id + " ha despertao al cajero permantente.");
		}

		if (nClientes > 3 * nCajerosOcasionales) {
			nCajerosOcasionales++;
			ocasional = new Cajero(this, false);
			notifyAll();
			System.out.println("CLIENTE: Se crea un nuevo cajero ocasional.");
		}

		System.out.println("CLIENTE: El cliente con id " + id + " espera en la cola.");
	}

	@Override
	public synchronized boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		if (nClientes > 0) {
			nClientes--; // atiendo cliente
			System.out.println("El cliente con id " + id + " ha sido atendido.");
			superCerrado = false;
			seAtiende = true;
		} else {
			System.out.println("Supermercado cerrado");
			seAtiende = false;
			cajeroPermanenteDisponible = false;
		}

		return seAtiende;
	}

	@Override
	public synchronized boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		nClientes--;

		if (nClientes < 3 * nCajerosOcasionales) {
			nCajerosOcasionales--;
			System.out
					.println("OCASIONAL: El cajero atiende al cliente " + id + " y cierra el cajero " + nCajerosOcasionales + ".");
			return false;
		}
		System.out.println("OCASIONAL: El cajero atiende al cliente " + id);
		return true;
	}

}
