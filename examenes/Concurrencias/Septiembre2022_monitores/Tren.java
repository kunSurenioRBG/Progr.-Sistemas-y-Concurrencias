package examenes.Concurrencias.Septiembre2022_monitores;

public class Tren {
	private static int N = 5;
	private boolean puedeSubir;
	private int nPasajeros;
	private boolean primerVagon;
	private boolean bajarPrimerVagon;
	private boolean bajarSegundoVagon;
	private boolean puedenSalir;

	public Tren() {
		puedeSubir = true;
		nPasajeros = 0;
		primerVagon = true;
		bajarPrimerVagon = false;
		bajarSegundoVagon = false;
		puedenSalir = false;
	}

	public synchronized void viaje(int id) throws InterruptedException {
		while (!puedeSubir) {
			wait(); // si el tren no tiene asientos libres o esta en marcha
		}

		nPasajeros++;
		if (primerVagon) {
			System.out.println(
					"El pasajero " + id + " esta sentado en el primer vagon. Hay " + nPasajeros + " pasajeros.");
			if (nPasajeros == N) {
				primerVagon = false; // el primer vagon esta lleno
			}
			// ***empieza el viaje***

			// compruebo si puedo bajarme del tren
			while (!bajarPrimerVagon) {
				wait();
			}

			nPasajeros--;
			System.out.println(
					"El pasajero " + id + " se baja del primer vagon. Hay " + nPasajeros + " pasajeros.");

			if (nPasajeros == N) {
				bajarPrimerVagon = false;
				bajarSegundoVagon = true;
				primerVagon = true; // primer vagon lleno
				notifyAll();
			}
		} else { // se va al segundo vagon
			System.out.println(
					"El pasajero " + id + " esta sentado en el segundo vagon. Hay " + nPasajeros + " pasajeros.");
			if (nPasajeros == 2 * N) {
				puedeSubir = false;
				puedenSalir = true; // para cuando lleguen al destino
				notifyAll(); // notifica al maquinista que puede salir
			}
			// ***empieza el viaje***

			// compruebo si puedo bajarme del tren
			while (!bajarSegundoVagon) {
				wait();
			}

			nPasajeros--;
			System.out.println(
					"El pasajero " + id + " se baja del segundo vagon. Hay " + nPasajeros + " pasajeros.");

			if (nPasajeros == 0) {
				bajarSegundoVagon = false;
				puedeSubir = true;
				notifyAll();
			}
		}
	}

	public synchronized void empiezaViaje() throws InterruptedException {
		System.out.println("        Maquinista:  empieza el viaje");
		while (!puedenSalir) {
			wait();
		}
	}

	public synchronized void finViaje() throws InterruptedException {
		System.out.println("        Maquinista:  fin del viaje");
		puedenSalir = false;
		bajarPrimerVagon = true;
		notifyAll();
	}
}
