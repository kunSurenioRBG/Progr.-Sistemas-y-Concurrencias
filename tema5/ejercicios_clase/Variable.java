package tema5.ejercicios_clase;


public class Variable {
    private int var;
    private boolean hayDato = false;

    public void nuevoDato(int v) {
        while (hayDato) {
            // Es un método de los threads que permite indicarle al hardware que está
            // ejecutando la tarea,
            // que puede interrumpirla y darle una oportunidad a otro thread.
            Thread.yield();
        }
        var = v;
        hayDato = true; // postprotocolo
    }

    public int leerDato() {
        while (!hayDato) {
            Thread.yield();
        }
        int local = var;
        hayDato = false;
        return local;
    }
}
