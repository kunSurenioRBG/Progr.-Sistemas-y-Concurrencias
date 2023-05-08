package practicas.practica5.prod_cons;


public class Buffer {

	private static int[] elem;
	private int p, c, nelem;

	public Buffer(int s) {
		elem = new int[s];

		// primer dato que se produzca vaya a la posicion 0
		p = 0;
		// primer dato que se consuma sea el de la posicion 0
		c = 0;
		nelem = 0;
	}

	public void producir(int dato) {
		// Pre
		while (nelem == elem.length) {
			Thread.yield();
		}
		// SC
		elem[p] = dato;
		System.out.println("Productor p:" + p + " dato: " + elem[p]);

		// Post
		p = (p + 1) % elem.length;
		nelem++;
	}

	public int consumir() {
		// Pre
		while (nelem == 0) {
			Thread.yield();
		}

		// SC
		int aux;
		aux = elem[c];

		// Post
		c = (c + 1) % elem.length;
		nelem--;

		return aux;

	}

}
