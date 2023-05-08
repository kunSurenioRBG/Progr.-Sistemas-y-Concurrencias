package practicas.practica9.recursos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Control {
	private int NUM;// numero total de recursos
	private Lock l = new ReentrantLock(true);
	private int rec; // recursos disponibles
	private List<Integer> lista = new ArrayList<>();

	// lleva los ids de los procesos que han solicitado recursos

	private Condition primero = l.newCondition();
	private Condition resto = l.newCondition();

	private int sig = -1; // guarda el id del siguiente proceso

	public Control(int num) {
		this.NUM = num;
		rec = NUM;
	}

	public void qRecursos(int id, int num) throws InterruptedException {
		l.lock();
		try {
			System.out.println("Proceso " + id + " solicita " + num + " recursos. Hay " + rec);
			lista.add(id);
			if (lista.size() > 1) { // hay gente
				while (sig != id) {
					resto.await();
				}
			}
			while (rec < num) {
				primero.await();
			}

			rec = rec - num;
			lista.remove(0);
			if (lista.size() > 0) {
				sig = lista.get(0);
				resto.signalAll();
			} else {
				sig = -1;
			}
		} finally {
			l.unlock();
		}
	}

	public void libRecursos(int id, int num) {
		l.lock();
		try {
			rec = rec + num;
			if (lista.size() > 0) {
				primero.signal();
			}
		} finally {
			l.unlock();
		}
	}
}

// CS1: espero hasta que haya suficientes recursos
// CS2: espero hasta que se han servido todos
// CS3: espero hasta que han llegado antes
