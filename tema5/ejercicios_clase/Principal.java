package ejercicios_clase;

public class Principal {
    public static void main(String[] args) {
        Jardin j = new Jardin();
        Puerta p0 = new Puerta(j);
        Puerta p1 = new Puerta(j);
        p0.start();
        p1.start();
        try {
            p0.join();
            p1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(j.valor());

    }
}
