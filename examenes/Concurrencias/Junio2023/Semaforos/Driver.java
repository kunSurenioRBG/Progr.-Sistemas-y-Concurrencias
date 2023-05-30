
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Cinta cinta=new Cinta();

		Generador g=new Generador(cinta);
		g.start();


		Pasajero pasajeros[] = new Pasajero[10]; 
		for(int i = 0; i< 10; i++) {
			pasajeros[i] = new Pasajero(i,cinta, i%2==0);
		}
		
		for(int i = 0; i< 10; i++) {
			pasajeros[i].start();
		}
	}
}


