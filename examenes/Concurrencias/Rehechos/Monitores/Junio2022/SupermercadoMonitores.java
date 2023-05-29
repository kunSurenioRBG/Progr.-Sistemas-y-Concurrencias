package examenes.Concurrencias.Rehechos.Monitores.Junio2022;

public class SupermercadoMonitores implements Supermercado {

	private int nClientes;
	private int nCajeros;

	private Cajero permanente;
	private Cajero ocasional;

	private boolean cerrado = false;
	private boolean cajeroPermanenteDespierto = false;
	private boolean clienteEspera = false;

	public SupermercadoMonitores() throws InterruptedException {
		permanente = new Cajero(this, true); // crea el primer cajero, el permanente
		permanente.start();
		nClientes = 0;
		nCajeros = 1;

	}

	@Override
	public synchronized void fin() throws InterruptedException {
		cerrado = true;
		System.out.println("Es supermercado ha cerrado (tengo clientes o no por atender todavia).");
	}

	@Override
	public synchronized void nuevoCliente(int id) throws InterruptedException {
		if (cerrado) {
			System.exit(-1);
		}

		nClientes++;
		System.out.println("Llega cliente " + id + ". Hay " + nClientes);
		if (nClientes == 1) {
			cajeroPermanenteDespierto = true; // cajero permanenete lo despierto
			System.out.println("Cliente " + id + " despierta al cajero permanente.");
			notifyAll();
		}

		if (nClientes >= 3 * nCajeros) {
			nCajeros++;
			ocasional = new Cajero(this, false);
			ocasional.start();
			System.out.println("Viene un nuevo cajero ocasional.");
		}

		while (clienteEspera) {
			wait();
		}
	}

	@Override
	public synchronized boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		while (!cajeroPermanenteDespierto) {
			wait();
		}

		while (nClientes == 0) {
			wait();
		}

		nClientes--;
		System.out.println("PERMANENTE atiende al cliente " + id + ". Quedan " + nClientes);
		clienteEspera = false;
		notify();
		if (nClientes == 0 && cerrado) {
			System.out.println("PERMANENTE ha acabado.");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public synchronized boolean ocasionalAtiendeCliente(int id) throws InterruptedException {

		if (nClientes == 0) {
			nCajeros--;
			System.out.println("Un cajero ocasional se larga.");
			return false;
		}
		nClientes--;
		System.out.println("OCASIONAL atienende al cliente " + id + ". Quedan " + nClientes);
		clienteEspera = false;
		notify();
		return true;
	}

}
