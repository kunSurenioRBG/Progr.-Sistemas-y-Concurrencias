package sensores;

import java.util.*;
public class Worker extends Thread{
	private  Random r = new Random();
	private Mediciones m;
	public Worker(Mediciones m) {
		this.m = m;
	}
	
	public void run() {
		while (true) {
			
			try {
				m.recogerMediciones();
				Thread.sleep(r.nextInt(300));
				m.finTrabajo();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
