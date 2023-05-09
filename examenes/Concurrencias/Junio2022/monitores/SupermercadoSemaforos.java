package examenes.Concurrencias.Junio2022.monitores;

public class SupermercadoSemaforos implements Supermercado {

	
	
	private Cajero permanente;
	private Cajero provisional;
	private int nuevoCliente;
	private boolean hayCliente;
	private int cajeroProvisional;
	private boolean superCerrado;
	private boolean cajeroPermantenteDespierto;

	
	
	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); //crea el primer cajero, el permanente
		provisional = new Cajero(this, false);
		permanente.start();		
		nuevoCliente = 0;
		hayCliente = false;
		cajeroProvisional = 1;
		superCerrado = true;
		cajeroPermantenteDespierto = true;
	}

	@Override
	public synchronized void fin() throws InterruptedException {
		
	}

	@Override
	public synchronized void nuevoCliente(int id) throws InterruptedException {
		nuevoCliente++;
		hayCliente = true;
		while (superCerrado) {
			wait();
		}
		cajeroPermantenteDespierto = true;
		
		if (nuevoCliente > 3 * cajeroProvisional) {
			cajeroProvisional++;
			provisional.start();
			notifyAll();
		}
	}

	@Override
	public synchronized boolean permanenteAtiendeCliente( int id) throws InterruptedException {
		
		
	}
		
	
	@Override
	public synchronized boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
			
	
		return true;//borrar
	}

}
