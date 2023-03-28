public class Principal {

	public static void main(String[] args) {
		Lago lago = new Lago();
		Rio r = new Rio(lago);
		Presa p0 = new Presa(0, lago);
		Presa p1 = new Presa(1, lago);
		r.start();
		p0.start();
		p1.start();
		
		try {
			r.join();
			p0.join();
			p1.join();
			
			System.out.println("Nivel del lago al final: "+lago.nivel());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
