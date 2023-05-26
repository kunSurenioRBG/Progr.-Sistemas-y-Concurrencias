package paseo_1v;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int N = 10;
		final int NAdulto = 6;//se puede cambiar el valor de NAdulto
		final int NMenor = 4; //y NMenor siempre NAdulto + NMenor = N
		Barca b = new Barca(N);
		Adulto[] adulto = new Adulto[NAdulto];
		Menor[] menor = new Menor[NMenor];
		for (int i = 0; i<adulto.length; i++) {
			adulto[i] = new Adulto(i,b);
		}
		for (int i = 0; i<menor.length; i++) {
			menor[i] = new Menor(i,b);
		}
		Barquero barquero = new Barquero(b);
		barquero.start();
		for (int i = 0; i<adulto.length; i++) {
			adulto[i].start();
		}
		for (int i = 0; i<menor.length; i++) {
			menor[i].start();
		}

	}

}
