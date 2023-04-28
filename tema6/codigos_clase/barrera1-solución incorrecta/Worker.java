package barrera1;
import java.util.*;
public class Worker extends Thread{
	private Gestor g;
	private int id;
	private static Random r = new Random();
	public Worker(int id, Gestor g){
		this.id = id;
		this.g = g;
	}
	
	public void run(){
		for (int i=0; i<25; i++){
			
			try {
				Thread.sleep(r.nextInt(100));
				g.llego(id,i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
