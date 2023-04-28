package lecesc;

import java.util.Random;

public class Lector extends Thread{
	private static Random r = new Random();
	private int id;
	private GestorBD gestor;
	public Lector(int id,GestorBD gestor) {
		this.id = id;
		this.gestor = gestor;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(r.nextInt(400));
				gestor.entraLector(id);//preprotocolo
				Thread.sleep(r.nextInt(200));//escritor en BD
				gestor.saleLector(id); //postprotocolo			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
