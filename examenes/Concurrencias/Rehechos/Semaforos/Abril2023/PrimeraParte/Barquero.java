package paseo_1v;

import java.util.*;
public class Barquero extends Thread{

	private Barca b;
	private Random r = new Random();
	public Barquero(Barca b) {
		this.b=b;
	}
	
	public void run() {
		
		try {
			b.esperoLleno();
			Thread.sleep(r.nextInt(1000));
			b.finViaje();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
