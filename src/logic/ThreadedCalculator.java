package logic;

/**
 * Created by eugen on 09.08.2015.
 */
public class ThreadedCalculator implements Runnable {
    private double a;
    private double b;
    private int n;
    private Function f;
    private Main main;

    public ThreadedCalculator(double a, double b, int n, Function f, Main main) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        this.main = main;
    }

    @Override
    public void run() {
        double result = new IntegralCalculator(a, b, n, f).calculate();
        main.sendResult(result);
    }
}
