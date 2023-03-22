
public class Puerta extends Thread {
    private Jardin jardin;

    public Puerta(Jardin jardin) {
        this.jardin = jardin;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            jardin.inc();
        }
    }
}
