package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;


import java.util.*;
public class Cliente extends Thread{
	
	private Random r = new Random();
	private int id;
	private Supermercado s;
	private int Total;
	
	public Cliente(Supermercado s, int id,int Total) {
		this.s = s;
		this.id = id;
		this.Total = Total;
	}

	public void run() {
		try {
			Thread.sleep(r.nextInt(1000));
			/*Compra 1 lata en la versi�n 1 porque
			Total es 1 y un n�mero aleatorio entre [0,1)
			ser� siempre 0*/
			s.comprarLatas(id,r.nextInt(Total)+1);
			Thread.sleep(r.nextInt(200));
			s.pagar(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
