package tema6.codigos_clase.prodcons;

import java.util.*;
public class Productor extends Thread{
	private Random r = new Random();
	private Buffer b;
	private int id;
	public Productor(Buffer b,int id){
		this.b = b;
		this.id = id;
	}
	public void run(){
		for (int i=0 ; i<25; i++){
			try {
				Thread.sleep(r.nextInt(10));
				b.almacenar(i,id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
