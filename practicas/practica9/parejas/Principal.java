package practicas.practica9.parejas;

public class Principal {
	public static void main(String[] args) {
		Sala sala = new Sala();
		Hombre[] h = new Hombre[10];
		Mujer[] m = new Mujer[10];
		for (int i = 0; i<h.length; i++)
			h[i] = new Hombre(i,sala);
		for (int i = 0; i<m.length; i++)
			m[i] = new Mujer(i,sala);
		for (int i = 0; i<h.length; i++)
			h[i].start();
		for (int i = 0; i<m.length; i++)
			m[i].start();
	}
	
	
}
