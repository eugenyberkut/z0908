package logic;

/**
 * Created by eugen on 09.08.2015.
 */
public class IntegralCalculator {
    private double a;
    private double b;
    private int n;
    private double h;
    private Function f;

    public IntegralCalculator(double a, double b, int n, Function f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        h = (b-a)/n;
    }

    public double calculate() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += f.f(a+i*h)*h;
        }
        return sum;
    }
}
