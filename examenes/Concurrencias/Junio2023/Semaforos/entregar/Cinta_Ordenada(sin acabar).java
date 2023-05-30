import java.util.concurrent.Semaphore;

public class Cinta {

	private Semaphore mutex;
	private Semaphore primeraRetira;
	private Semaphore turistaRetira;
	private Semaphore primeraRetiraFinal;
	private Semaphore turistaRetiraFinal;
	private Semaphore mutex2;

	private int maletasPrimera, maletasTurista, primeraEsperando;
	
	public Cinta() {
		maletasPrimera = 0;
		maletasTurista = 0;
		primeraEsperando = 0;
		mutex = new Semaphore(1);
		primeraRetira = new Semaphore(0);
		turistaRetira = new Semaphore(0);
		primeraRetiraFinal = new Semaphore(0);
		turistaRetiraFinal = new Semaphore(0);
		mutex2 = new Semaphore(1);
	}
	
	public void poner(boolean primeraClase) throws InterruptedException {
		if (primeraClase == true) {
			//primera clase
			mutex.acquire();
			maletasPrimera++;
			primeraEsperando++;
			System.out.println("Generador pone maleta de primera clase. Total primera: "+maletasPrimera+" Total turista: "+maletasTurista);
			primeraRetira.release();
			mutex.release();

		} else {
			//turista
			mutex.acquire();
			maletasTurista++;
			System.out.println("Generador pone maleta de clase turista. Total primera: "+maletasPrimera+" Total turista: "+maletasTurista);
			turistaRetira.release();
			mutex.release();
		}
	}
	

	public void qRetirarPrimera(int pasajeroId) throws InterruptedException {
		primeraRetira.acquire();
		System.out.println("***Pas. primera "+pasajeroId+" quiere maleta***");
		System.out.println("***Pas. primera "+pasajeroId+" entra a la cinta***");
		primeraRetiraFinal.release();
	}
	
	public void qRetirarTurista(int pasajeroId) throws InterruptedException {
		if (primeraEsperando > 0) {
			turistaRetira.acquire();
		} else {
			System.out.println("Pas. turista "+pasajeroId+" entra a la cinta");
			turistaRetiraFinal.release();
		}
	}
	
	public void fRetirarPrimera(int pasajeroId) throws InterruptedException {
		mutex2.acquire();
		primeraRetiraFinal.acquire();
		mutex.acquire();
		maletasPrimera--;
		primeraEsperando--;
		System.out.println("***Pas. primera "+pasajeroId+" coge su maleta***");
		primeraRetiraFinal.release();
		mutex2.release();
		mutex.release();

	}
	public void fRetirarTurista(int pasajeroId) throws InterruptedException {
		mutex2.acquire();
		turistaRetiraFinal.acquire();
		mutex.acquire();
		maletasTurista--;
		System.out.println("Pas. turista "+pasajeroId+" coge su maleta");
		turistaRetiraFinal.release();
		mutex2.release();
		mutex.release();
	}
}
