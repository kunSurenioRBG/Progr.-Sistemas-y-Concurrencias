package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;


public class Principal {

	public static void main(String[] args) {
		final int Total = 10;
		Supermercado s = new Supermercado1(Total);
		/* Comenta la línea superior y descomenta la inferior 
		 * para probar la implementación del ejercicio 2 */
		//Supermercado s = new Supermercado2(Total);
		Cajero c = new Cajero(s);
		Reponedor sum = new Reponedor(s);
		Cliente[] cliente = new Cliente[30];
		for (int i=0; i<cliente.length;i++) {
			cliente[i] = new Cliente(s,i,1);
			/* Comenta la línea superior y descomenta la inferior 
		 	 * para probar la implementación del ejercicio 2 */
			//cliente[i] = new Cliente(s,i,Total);
		}
		c.start();
		sum.start();
		for (int i=0; i<cliente.length;i++) {
			cliente[i].start();
		}
		
		for (int i=0; i<cliente.length; i++) {
			try {
				cliente[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		c.interrupt();
		sum.interrupt();
		
	}

}
