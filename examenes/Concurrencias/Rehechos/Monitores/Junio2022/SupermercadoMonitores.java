package examenes.Concurrencias.Rehechos.Monitores.Junio2022;

public class SupermercadoMonitores implements Supermercado{

	private int nClientes;

	private Cajero permanente;

	
	
	public SupermercadoMonitores() throws InterruptedException {
		permanente = new Cajero(this, true); //crea el primer cajero, el permanente
		permanente.start();
		
		//TODO
	}
	
	@Override
	public synchronized void fin() throws InterruptedException {
		
		
	}

	@Override
	public synchronized void nuevoCliente(int id) throws InterruptedException {
		
		
	}

	@Override
	public synchronized boolean permanenteAtiendeCliente(int id) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;//borrar
	}

	@Override
	public synchronized boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;//borrar
	}

}
