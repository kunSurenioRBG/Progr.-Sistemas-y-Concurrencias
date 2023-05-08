package practicas.practica7.mRusa;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coche c = new Coche(5);
		Pasajero[] p = new Pasajero[25];
		for (int i = 0; i<p.length; i++)
			p[i] = new Pasajero(c,i);
		c.start();
		for (int i = 0; i<p.length; i++)
			p[i].start();
	}

}
