package paseo_2v;

import java.util.*;
public class Barquero extends Thread{

	private Barca b;
	private Random r = new Random();
	private int nIter;
	public Barquero(Barca b,int nIter) {
		this.b=b;
		this.nIter = nIter;
	}
	
	public void run() {
		
		try {
			for (int i=0; i<nIter; i++) {
				b.esperoLleno();
				Thread.sleep(r.nextInt(1000));
				b.finViaje();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
