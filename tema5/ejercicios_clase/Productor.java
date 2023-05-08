package tema5.ejercicios_clase;


public class Productor extends Thread {
    private Variable var;

    public Productor(Variable var) {
        this.var = var;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(var.leerDato());
        }
    }
}
