package lecesc;

public class Principal {

	public static void main(String[] args) {
		int NE = 3;
		int NL = 50;
		// TODO Auto-generated method stub
		GestorBD gestor = new GestorBD();
		Escritor[] escritor = new Escritor[NE];
		Lector[] lector = new Lector[NL];
		for (int i = 0; i<escritor.length; i++)
			escritor[i] = new Escritor(i,gestor);
		for (int i = 0; i<lector.length; i++)
			lector[i] = new Lector(i,gestor);
		for (int i = 0; i<escritor.length; i++)
			escritor[i].start();
		for (int i = 0; i<lector.length; i++)
			lector[i].start();

	}

}
