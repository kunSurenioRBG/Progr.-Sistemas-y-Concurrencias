package practicas.practica9.fiesta;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bandeja pasteleria = new Bandeja();
		Pastelero pastelero = new Pastelero(pasteleria);
		Niño[] niño = new Niño[30];
		for (int i = 0; i<niño.length; i++){
			niño[i] = new Niño(pasteleria,i);
		}
		pastelero.start();
		for (int i = 0; i<niño.length; i++){
			niño[i].start();
		
		}
		
	}

}
