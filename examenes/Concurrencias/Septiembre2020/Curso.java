import java.util.concurrent.Semaphore;

public class Curso {

	// Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;

	// Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;

	private Semaphore mutex;
	private Semaphore puedeEntrarIniciacion;
	private int alumnosIniciacion;
	private Semaphore puedeEntrarAvanzada;
	private int alumnosAvanzado;
	private Semaphore estaLlenoAvanzado;

	public Curso() {
		mutex = new Semaphore(1);
		puedeEntrarIniciacion = new Semaphore(1);
		alumnosIniciacion = 0;
		puedeEntrarAvanzada = new Semaphore(1);
		alumnosAvanzado = 0;
		estaLlenoAvanzado = new Semaphore(0);
	}

	// El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de
	// iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException {
		// Espera si ya hay 10 alumnos cursando esta parte
		puedeEntrarIniciacion.acquire();
		mutex.acquire();

		alumnosIniciacion++;
		// Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de
		// iniciacion
		System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");

		if (alumnosIniciacion < MAX_ALUMNOS_INI) {
			puedeEntrarIniciacion.release();
		}
		mutex.release();
	}

	// El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException {
		mutex.acquire();
		alumnosIniciacion--;
		// Mensaje a mostrar para indicar que el alumno ha terminado la parte de
		// principiantes
		System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");

		// Libera la conexion para que otro alumno pueda usarla
		if (alumnosIniciacion == MAX_ALUMNOS_INI - 1) {
			puedeEntrarIniciacion.release();
		}

		mutex.release();
	}

	/*
	 * El alumno tendra que esperar:
	 * - si ya hay un grupo realizando la parte avanzada
	 * - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException {
		// Espera a que no haya otro grupo realizando esta parte
		puedeEntrarAvanzada.acquire();
		mutex.acquire();
		// Espera a que haya tres alumnos conectados
		alumnosAvanzado++;
		if (alumnosAvanzado == ALUMNOS_AV) {
			// Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
			System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
			estaLlenoAvanzado.release();
		} else {
			// Mensaje a mostrar si el alumno tiene que esperar al resto de miembros en el
			// grupo
			System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
			puedeEntrarAvanzada.release();
		}
		mutex.release();
	}

	/*
	 * El alumno:
	 * - informa que ya ha terminado de cursar la parte avanzada
	 * - espera hasta que los tres miembros del grupo hayan terminado su parte
	 */
	public void finAvanzado(int id) throws InterruptedException {
		// Espera a que los 3 alumnos terminen su parte avanzada
		estaLlenoAvanzado.acquire();
		mutex.acquire();

		alumnosAvanzado--;
		// Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del
		// grupo terminen
		System.out.println("PARTE AVANZADA: Alumno " + id + " termina su parte del proyecto. Espera al resto");

		if (alumnosAvanzado > 0) {
			estaLlenoAvanzado.release();
		} else if (alumnosAvanzado == 0) {
			// Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
			System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO");
			puedeEntrarAvanzada.release();
		}
		mutex.release();
	}
}
