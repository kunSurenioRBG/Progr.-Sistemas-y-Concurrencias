package examenes.Concurrencias.Rehechos.Monitores.Mayo2023;


public interface Supermercado {
	public  void comprarLatas(int id,int num)throws InterruptedException;
	public  void esperarPeticion()throws InterruptedException;
	public  void nuevoSuministro() throws InterruptedException;
	public  void pagar(int id) throws InterruptedException;
	public  void cobrar() throws InterruptedException;

}
