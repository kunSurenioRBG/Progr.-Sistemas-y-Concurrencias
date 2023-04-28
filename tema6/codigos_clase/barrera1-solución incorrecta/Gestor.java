package barrera1;

import java.util.concurrent.*;
public class Gestor {
	private int tam,num = 0;
	   
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore espera = new Semaphore(0,true);
   
    public Gestor(int tam){
        this.tam = tam;
    }
	
	public void llego(int id,int iter) throws InterruptedException{
		mutex.acquire();   
        num++;
        System.out.println("Iter: "+iter+" Ha llegado trabajador "+id+ " hay "+num);
        if (num < tam) espera.acquire();
        num--;
        if (num > 0) espera.release();
        System.out.println("Trabajador "+id+ " sale. Hay "+num);
        mutex.release();
	}
}
//CS- worker no puede comenzar la iteración i hasta que todos hayan terminado la
// iteración i-1