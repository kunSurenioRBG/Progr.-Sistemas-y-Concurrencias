package practicas.practica8.aseos;

public class Aseos {
	private boolean limpiando;
	private int clientesDentro;
	private boolean quieroLimpiar;

	public Aseos() {
		clientesDentro = 0;
		limpiando = false;
	}

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est�
	 * trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando
	 * o
	 * est� esperando para poder limpiar los aseos
	 * 
	 */
	public synchronized void entroAseo(int id) throws InterruptedException {
		// si estan limpiando, el cliente se espera (bloquea)
		if (limpiando || quieroLimpiar) {
			wait();
		}
		clientesDentro++;
		System.out.println("Cliente " + id + " dentro. Total clientes dentro: " + clientesDentro);
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public synchronized void salgoAseo(int id) {
		clientesDentro--;
		System.out.println("\t Cliente " + id + " fuera. Total clientes dentro: " + clientesDentro);
		if (clientesDentro == 0) {
			notify();
		}
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que
	 * no
	 * haya ning�n cliente.
	 * 
	 */
	public synchronized void entraEquipoLimpieza() throws InterruptedException {
		// usamos sentencia while porque notify no asegura siempre que salga del wait
		while (clientesDentro > 0) {
			wait();
		}
		limpiando = true;
		quieroLimpiar = true;
		System.out.println("Equipo de limpieza dentro del aseo");
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando sale de los aseos
	 * 
	 * 
	 */
	public synchronized void saleEquipoLimpieza() {
		System.out.println("Sale equipo de limpieza del aseo");
		limpiando = false;
		quieroLimpiar = false;
		notifyAll();
	}
}
