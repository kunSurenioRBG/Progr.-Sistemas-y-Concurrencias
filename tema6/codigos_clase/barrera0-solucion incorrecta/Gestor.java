package barrera0;

import java.util.concurrent.*;
public class Gestor {
	private int tam,num = 0;
    private Semaphore mutex = new Semaphore(1,true);

	
	public Gestor(int tam){
		this.tam = tam;
	}
	
	public void llego(int id,int iter) throws InterruptedException{
		mutex.acquire();   
        num++;
        System.out.println("Iter: "+iter+": Ha terminado el trabajador "+id+ " hay "+num);
        if (num == tam) {
            System.out.println("Ya estamos todos "+id+ " hay "+num);
            num = 0;
        }
        mutex.release();
	}
}
//CS- worker no puede comenzar la iteración i hasta que todos hayan terminado la
// iteración i-1