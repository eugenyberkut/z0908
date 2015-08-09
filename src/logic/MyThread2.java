package logic;

/**
 * Created by eugen on 09.08.2015.
 */
public class MyThread2 implements Runnable{
    private String name;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(getName() + " " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.err.println("interrupt");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
