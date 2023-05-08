package practicas.practica6.sensores;

import java.util.concurrent.*;


public class Mediciones {

	private int num = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore sWorker = new Semaphore(0);
	private Semaphore[] sSensor = new Semaphore[3];

	public Mediciones(){
		for (int i = 0; i < sSensor.length; i++) {
			sSensor[i] = new Semaphore(0);
		}
	}
	
	public void nuevaMedicion(int id) throws InterruptedException {
		//el sensor id deja una nueva medición
		mutex.acquire(); // 
		System.out.println("Sensor "+id+" deja sus mediciones");
		num++;
		if (num==3) {
			sWorker.release();
		}
		mutex.release();
		sSensor[id].acquire();
		
	}
	
	public void recogerMediciones() throws InterruptedException  {
		//el worker coge las tres mediciones
		sWorker.acquire();
		mutex.acquire();
		System.out.println("Worker ha recogido las mediciones");
		num=0;
		mutex.release();
	}
	
	public void finTrabajo() {
		//lo llama el worker cuando ha procesado las mediciones
		System.out.println("Worker ha procesado las mediciones");
		for (int i = 0; i < sSensor.length; i++) {
			sSensor[i].release();
		}
	}
	
	//CS-Worker: No puede recoger las mediciones hasta que no están las tres
	//CS-Sensor-i: No puede medir hasta que el worker no ha procesado
	//las medidas de la interación anterior

}
