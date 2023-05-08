package tema5.ejercicios_clase.semaphore;

public class Principal{
    public static void main(String[] args) {
        Jardin j = new Jardin();
        Puerta[] p = new Puerta[10];
        
        for (int i = 0; i < p.length; i++) {
            p[i] = new Puerta(j,i);
        }
        
        for (int i = 0; i < p.length; i++) {
            p[i].start();
        }

        try {
            for (int i = 0; i < p.length; i++) {
                p[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
