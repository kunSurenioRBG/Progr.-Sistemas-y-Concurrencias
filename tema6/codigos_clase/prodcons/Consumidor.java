package  tema6.codigos_clase.prodcons;

import java.util.*;
public class Consumidor extends Thread{
	private Random r = new Random();
	private Buffer b;
	public Consumidor(Buffer b){
		this.b = b;
	}
	public void run(){
		for (int i=0 ; i<50; i++){
			
			try {
				b.extraer();
				Thread.sleep(r.nextInt(10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
