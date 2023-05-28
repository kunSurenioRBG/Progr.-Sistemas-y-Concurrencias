package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;


import java.util.*;
public class Cajero extends Thread{
	
	private Random r = new Random();
	private Supermercado s;
	public Cajero(Supermercado s) {
		this.s = s;
	}
	
	public void run() {
		boolean fin = false;
		while (!fin) {
			try {
				s.cobrar();
				Thread.sleep(r.nextInt(200));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				fin = true;
			}
		}
	}

}
