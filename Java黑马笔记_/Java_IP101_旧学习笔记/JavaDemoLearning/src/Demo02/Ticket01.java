package Demo02;

public class Ticket01 {
    public static void main(String[] args) {
        Runnable run = new RunnableImpl();
        System.out.println("run: " + run);

        Thread t0 = new Thread(run);
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);

        t0.start();
        t1.start();
        t2.start();
    }
}
