package paseo_2v;

import java.util.*;
public class Adulto extends Thread{

	private static Random r = new Random();
	private Barca b;
	private int id;

	public Adulto(int id, Barca b) {
		this.id = id;
		this.b = b;
	}
	
	public void run() {
		try {
				Thread.sleep(r.nextInt(200));
				b.subeAdulto(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
