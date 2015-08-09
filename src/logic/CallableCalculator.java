package logic;

import java.util.concurrent.Callable;

/**
 * Created by eugen on 09.08.2015.
 */
public class CallableCalculator implements Callable<Double> {
    private double a;
    private double b;
    private int n;
    private Function f;

    public CallableCalculator(double a, double b, int n, Function f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    @Override
    public Double call() {
        return new IntegralCalculator(a,b,n,f).calculate();
    }
}
