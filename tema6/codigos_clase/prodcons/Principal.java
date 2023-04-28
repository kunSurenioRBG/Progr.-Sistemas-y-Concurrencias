package tema6.codigos_clase.prodcons;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Buffer b = new Buffer(5);
		Consumidor c = new Consumidor(b);
		Productor p0 = new Productor(b,0);
		Productor p1 = new Productor(b,1);
		c.start();p0.start();p1.start();

	}

}
