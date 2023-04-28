package pajaritos;

public class Principal {

	public static void main(String[] args) {
		Nido n = new Nido();
		Papa p = new Papa(n,0);
		Papa m = new Papa(n,1);
		Bebe[] b = new Bebe[10];
		for (int i=0; i<b.length; i++) {
			b[i] = new Bebe(n,i);
		}
		p.start();
		m.start();
		for (int i=0; i<b.length; i++) {
			b[i].start();
		}
	}
}
