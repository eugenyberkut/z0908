package logic;

/**
 * Created by eugen on 09.08.2015.
 */
public class MyFunction implements Function{
    @Override
    public double f(double x) {
        return Math.sin(x);
    }
}
