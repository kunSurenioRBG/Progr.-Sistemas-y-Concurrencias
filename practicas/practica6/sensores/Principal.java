package sensores;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Mediciones m = new Mediciones();
		Worker w = new Worker(m);
		Sensor[] s = new Sensor[3];
		for (int i=0; i<3; i++) {
			s[i] = new Sensor(m,i);
		}
		w.start();
		for (int i=0; i<3; i++) {
			s[i].start();
		}
	}

}
