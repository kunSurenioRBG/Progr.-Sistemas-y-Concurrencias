
public class Principal_Productor {
    public static void main(String[] args) {
        Variable var = new Variable();
        Productor p = new Productor(var);
        Consumidor c = new Consumidor(var);
        p.start();
        c.start();
        try {
            p.start();
            c.start();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(var.leerDato());
        
    }
}
