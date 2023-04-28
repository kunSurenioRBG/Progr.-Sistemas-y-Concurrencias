package practicas.practica6.fumadores;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mesa m = new Mesa();
		Agente a = new Agente(m);
		Fumador[] f = new Fumador[3];
		for (int i=0; i<f.length; i++)
			f[i] = new Fumador(m,i);
		a.start();
		for (int i=0; i<f.length; i++)
			f[i].start();
	}

}
