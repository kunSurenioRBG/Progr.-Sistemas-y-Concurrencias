public class Principal {

	public static void main(String[] args) {
		Barca b = new Barca();
		Capitan p = new Capitan(b);
		Pasajerito[] pas = new Pasajerito[18];
		for (int i=0; i<pas.length; i++){
			pas[i] = new Pasajerito(i,i%2==0?0:1,b);
		}
		p.start();
		for (int i=0; i<pas.length; i++){
			pas[i].start();
		}

	}

}
