package tema5.ejercicios_clase;

public class Consumidor extends Thread {
    private Variable var;

    public Consumidor(Variable var) {
        this.var = var;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(var.leerDato());
        }
    }
}
