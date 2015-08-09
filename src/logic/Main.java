package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by eugen on 09.08.2015.
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();
    }

    private void demo5() {
        int nt = 100;
        double a = 0;
        double b = Math.PI;
        double delta = (b-a)/nt;
        int n = 100_000_000;
        Function f = new MyFunction();
        List<Future<Double>> futures = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < nt; i++) {
            CallableCalculator calculator = new CallableCalculator(a + i * delta, a + (i + 1) * delta, n / nt, f);
            Future<Double> future = es.submit(calculator);
            futures.add(future);
        }
        es.shutdown();
        double integral = 0;
        try {
            for (Future<Double> future : futures) {
                integral += future.get();
            }
            System.out.println("integral = " + integral);
        } catch (Exception ex) {
            System.err.println("exception");
        }
    }

    private void demo4() {
        int nt = 100;
        double a = 0;
        double b = Math.PI;
        double delta = (b-a)/nt;
        int n = 100_000_000;
        Function f = new MyFunction();
        long start = System.currentTimeMillis();
        for (int i = 0; i < nt; i++) {
            new Thread(new ThreadedCalculator(a+i*delta,a+(i+1)*delta,n/nt,f,this)).start();
        }
        synchronized (this) {
            try {
                while (finishedThreads < nt) {
                    wait();
                }
            } catch (InterruptedException ex) {
                System.err.println("Interrupted");
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("result = " + integral);
        System.out.println(finish-start);
    }

    private void demo3() {
        Function f = new MyFunction();
        long start = System.currentTimeMillis();
        IntegralCalculator calculator = new IntegralCalculator(0,Math.PI,10000000,f);
        double result = calculator.calculate();
        long finish = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finish - start);
    }

    private void demo2() {
        MyThread2 mt1 = new MyThread2();
        mt1.setName("first");
        MyThread2 mt2 = new MyThread2();
        mt2.setName("second");
        new Thread(mt1).start();
        new Thread(mt2).start();
        System.out.println("hello");
    }

    private void demo1() {
        MyThread mt1 = new MyThread();
        mt1.setName("first");
        MyThread mt2 = new MyThread();
        mt2.setName("second");
        mt1.start();
        mt2.start();
        System.out.println("Hello");
    }

    public synchronized void sendResult(double result) {
        integral += result;
        finishedThreads++;
        notify();
    }

    double integral = 0;
    int finishedThreads = 0;
}
