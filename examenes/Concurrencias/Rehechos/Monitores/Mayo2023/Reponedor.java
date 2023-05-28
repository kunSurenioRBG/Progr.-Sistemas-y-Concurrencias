package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;


import java.util.*;
public class Reponedor extends Thread{
	
	private Random r = new Random();
	private Supermercado s;
	
	public Reponedor(Supermercado s) {
		this.s = s;
	}

	public void run() {
		boolean fin = false;
		while (!fin) {
			try {
				s.esperarPeticion();
				Thread.sleep(r.nextInt(200));
				s.nuevoSuministro();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				fin=true;
			}
		}
	}
}
