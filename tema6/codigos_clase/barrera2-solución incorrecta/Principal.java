package barrera2;

public class Principal {

	public static void main(String[] args){
		Gestor g = new Gestor(10);
		Worker[] c = new Worker[10];
		for (int i=0; i<c.length; i++){
			c[i] = new Worker(i,g);
		}
		for (int i=0; i<c.length; i++){
			c[i].start();
		}
	}
}
