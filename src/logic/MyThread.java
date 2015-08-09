package logic;

/**
 * Created by eugen on 09.08.2015.
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName()+" "+i);
        }
    }
}
