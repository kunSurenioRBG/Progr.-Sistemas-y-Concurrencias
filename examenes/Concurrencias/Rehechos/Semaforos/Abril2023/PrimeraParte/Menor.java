package paseo_1v;

import java.util.Random;

public class Menor extends Thread{

	private static Random r = new Random();
	private Barca b;
	private int id;
	public Menor(int id, Barca b) {
		this.id = id;
		this.b = b;
	}
	
	public void run() {
		try {
			Thread.sleep(r.nextInt(200));
			b.subeMenor(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
