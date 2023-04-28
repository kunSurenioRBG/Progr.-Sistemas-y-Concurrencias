package lecesc;

import java.util.*;
public class Escritor extends Thread {
	private static Random r = new Random();
	private int id;
	private GestorBD gestor;
	public Escritor(int id,GestorBD gestor) {
		this.id = id;
		this.gestor = gestor;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(r.nextInt(400));
				gestor.entraEscritor(id);//preprotocolo
				Thread.sleep(r.nextInt(200));//escritor en BD
				gestor.saleEscritor(id); //postprotocolo			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
