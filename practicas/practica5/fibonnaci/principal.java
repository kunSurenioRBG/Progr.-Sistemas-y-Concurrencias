package practicas.practica5.fibonnaci;

public class principal {
    public static void main(String[] args) {
        int N = 10;
        fibonacci f = new fibonacci(N);
        f.start();

        try {
            f.join();
            System.out.println("Fibonacci de "+N+" = "+f.getFibN());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
