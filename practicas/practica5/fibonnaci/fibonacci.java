package practicas.practica5.fibonnaci;

public class fibonacci extends Thread {
    private int n, fib_n, fib_n_1;

    public fibonacci(int n) {
        this.n = n;
    }

    public int getFibN() {
        return fib_n;
    }

    public int getFibN_1() {
        return fib_n_1;
    }

    public void run() {
        if (n == 0) {
            fib_n = 0;
            fib_n_1 = 0;
        } else if (n == 1) {
            fib_n = 1;
            fib_n_1 = 0;
        }else{
            fibonacci f_n_1 = new fibonacci(n-1);

            f_n_1.start();

            try {
                f_n_1.join();
                fib_n_1 = f_n_1.getFibN();
                fib_n = f_n_1.getFibN() + f_n_1.getFibN_1();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
