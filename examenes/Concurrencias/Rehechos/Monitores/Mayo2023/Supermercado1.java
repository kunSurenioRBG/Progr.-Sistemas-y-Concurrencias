package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;

public class Supermercado1 implements Supermercado {

	private int Total;
	private int nLatas;
	private int cliente;

	private boolean reponedorRepone = false;
	private boolean cajeroDisponible = true;
	private boolean puedeCobrarCliente = false;
	private boolean poneLata = false;

	public Supermercado1(int total) {
		this.Total = total; // número máximo de latas
		this.nLatas = total; // número de latas disponibles
		cliente = -1; // id del cliente atendido por el cajero
	}

	/*
	 * Este método lo utiliza un Cliente para coger num latas del lineal.
	 * En el ejercicio 1, num siempre es 1.
	 */
	public synchronized void comprarLatas(int id, int num) throws InterruptedException {
		while (reponedorRepone) {
			wait();
		}
		nLatas--;
		cliente = id;
		System.out.println("Cliente " + id + " compra en el supermercado " + num + " latas. Quedan: " + nLatas);

		if (nLatas == 0) {
			System.out.println("Cliente " + id + " avisa al reponedor");
			reponedorRepone = true;
			notifyAll(); // notify() tambien podria ser, dado que solo es una persona

		}
	}

	/*
	 * Este método lo utiliza el Reponedor para esperar el aviso de un
	 * Cliente por falta de latas.
	 */
	public synchronized void esperarPeticion() throws InterruptedException {
		while (!reponedorRepone) {
			wait();
		}
		System.out.println("El reponedor ha sido despertado");
	}

	/*
	 * Este método lo utiliza el Reponedor para reponer las latas,
	 * asegurándose que hay N_LATAS en el supermercado.
	 */
	public synchronized void nuevoSuministro() {
		System.out.println("El reponedor pone nuevos suministros");
		nLatas = Total;
		reponedorRepone = false;
		notifyAll();
	}

	/*
	 * Este método lo utiliza un Cliente para pagar en la caja.
	 */
	public synchronized void pagar(int id) throws InterruptedException {
		while (!cajeroDisponible) {
			wait();
		}
		System.out.println("Cliente " + id + " empieza a pagar");
		poneLata = true;
		cajeroDisponible = false;
		notifyAll();

		while (!puedeCobrarCliente) {
			wait();
		}
		System.out.println("Cliente " + id + " ha terminado de pagar y se va");
		cajeroDisponible = true;
		puedeCobrarCliente = false;
		poneLata = false;
		notifyAll();
	}

	/*
	 * Este método lo utiliza el Cajero para cobrar a un Cliente.
	 */
	public synchronized void cobrar() throws InterruptedException {
		while (!poneLata) {
			wait();
		}
		System.out.println("Cajero cobra al cliente " + cliente);
		puedeCobrarCliente = true;
		System.out.println("Cajero está disponible para otro cliente");
		notifyAll(); // notify() tambien podria ser, dado que solo es una persona
	}
}
