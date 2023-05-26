package paseo_2v;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NIter = 3;
		final int N = 10;
		final int menoresEnBarca = 4;
		final int NAdulto = (N-menoresEnBarca)*NIter;
		final int NMenor = menoresEnBarca*NIter;
		
		
		Barca b = new Barca(N,menoresEnBarca);
		Adulto[] adulto = new Adulto[NAdulto];
		Menor[] menor = new Menor[NMenor];
		for (int i = 0; i<adulto.length; i++) {
			adulto[i] = new Adulto(i,b);
		}
		for (int i = 0; i<menor.length; i++) {
			menor[i] = new Menor(i,b);
		}
		Barquero barquero = new Barquero(b,NIter);
		barquero.start();
		for (int i = 0; i<adulto.length; i++) {
			adulto[i].start();
		}
		for (int i = 0; i<menor.length; i++) {
			menor[i].start();
		}

	}

}

