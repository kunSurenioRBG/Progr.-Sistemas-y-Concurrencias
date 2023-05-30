
public class Cinta {
	private boolean primeraVaRetirar = false;
	private boolean turistaVaRetirar = false;
	private boolean primeraRetira = false;
	private boolean turistaRetira = false;

	private int maletasPrimera, maletasTurista, primeraEsperando;
	
	public Cinta() {
		maletasPrimera = 0;
		maletasTurista = 0;
		primeraEsperando = 0;
	}
	
	public synchronized void poner(boolean primeraClase) throws InterruptedException {
		if (primeraClase == true) {
			//primera clase
			maletasPrimera++;
			primeraEsperando++;
			System.out.println("Generador pone maleta de primera clase. Total primera: "+maletasPrimera+" Total turista: "+maletasTurista);
			primeraVaRetirar = true;
			notifyAll();
		} else {
			//turista
			maletasTurista++;
			System.out.println("Generador pone maleta de clase turista. Total primera: "+maletasPrimera+" Total turista: "+maletasTurista);
			turistaVaRetirar = true;
			notifyAll();
		}
	}
	

	public synchronized void qRetirarPrimera(int pasajeroId) throws InterruptedException {
		while (!primeraVaRetirar) {
			wait();
		}
		System.out.println("***Pas. primera "+pasajeroId+" quiere maleta***");
		System.out.println("***Pas. primera "+pasajeroId+" entra a la cinta***");
		primeraRetira = true;
		primeraVaRetirar = false;
		notifyAll();
	}
	
	public synchronized void qRetirarTurista(int pasajeroId) throws InterruptedException {
		while (!turistaVaRetirar || primeraEsperando > 0) {
			wait();
		}
		System.out.println("Pas. turista "+pasajeroId+" entra a la cinta");
		turistaRetira = true;
		turistaVaRetirar = false;
		notifyAll();
	}
	
	public synchronized void fRetirarPrimera(int pasajeroId) throws InterruptedException {
		while (!primeraRetira) {
			wait();
		}
		maletasPrimera--;
		primeraEsperando--;
		primeraRetira = false;
		notifyAll();
		System.out.println("***Pas. primera "+pasajeroId+" coge su maleta***");
	}
	public synchronized void fRetirarTurista(int pasajeroId) throws InterruptedException {
		while (!turistaRetira) {
			wait();
		}
		maletasTurista--;
		turistaRetira = false;
		notifyAll();
		System.out.println("Pas. turista "+pasajeroId+" coge su maleta");
	}

}
