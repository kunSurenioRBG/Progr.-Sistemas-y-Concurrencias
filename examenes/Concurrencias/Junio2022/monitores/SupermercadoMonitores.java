package examenes.Concurrencias.Junio2022.monitores;

public class SupermercadoMonitores implements Supermercado {

	private Cajero permanente;
	private Cajero ocasional;

	// Condiciones de Sincronizacion
	private boolean cajeroPermanenteEspera;
	private boolean clienteEspera;
	private boolean superAbierto;

	private int clientes;
	private int nCajerosOcasionales;

	public SupermercadoMonitores() throws InterruptedException {
		permanente = new Cajero(this, true); // crea el primer cajero, el permanente
		permanente.start();

		ocasional = null;

		cajeroPermanenteEspera = true;
		clienteEspera = true;
		superAbierto = true;

		clientes = 0;
		nCajerosOcasionales = 1;
	}

	@Override
	public synchronized void fin() throws InterruptedException {
		superAbierto = false;
		System.out.println("Super esta cerrado");

		while (clientes > 0) { // si hay clientes por atender, espero antes de cerrar
			wait();
		}
	}

	@Override
	public synchronized void nuevoCliente(int id) throws InterruptedException {
		if (!superAbierto) {
			System.exit(-1);
		} else {
			clientes++;
			System.out.println("El cliente " + id + " acaba de entrar al super");
			cajeroPermanenteEspera = true; // cajero permanente se despierta

			if (clientes > 3 * nCajerosOcasionales) {
				nCajerosOcasionales++;
				ocasional = new Cajero(this, false);
				ocasional.start();
			}

			while (clienteEspera) {
				wait();
			}
		}
	}

	@Override
	public synchronized boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		if (clientes > 0) { // si hay clientes, se atienden
			System.out.println("Cajero permanente atiende al cliente " + id);
			clientes--;
			return true;
		} else if (clientes == 0 && !superAbierto) { // no hay clientes y el super esta cerrado
			System.out.println("No hay clientes o el super esta cerrado");
			notifyAll(); // libera todos los hilos puesto que, una vez cerrado el super, el resto de
							// hilos esperando no cumplen sus condiciones
			return false;
		} else { // no hay clientes, cajero espera a que halla
			System.out.println("Cajero permanente se mantiene en espera");
			while (cajeroPermanenteEspera) {
				wait();
			}
		}
		return true;
	}

	@Override
	public synchronized boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		if (clientes > 0) {
			clientes--;
			System.out.println("Cajero ocasional se marcha: Quedan " + nCajerosOcasionales);
		} else {
			nCajerosOcasionales--; // el cajero ocasional puede marcharse, no hace falta
			System.out.println("Cajero ocasional se marcha");
			notifyAll();
			return false;
		}
		return true;
	}

}
