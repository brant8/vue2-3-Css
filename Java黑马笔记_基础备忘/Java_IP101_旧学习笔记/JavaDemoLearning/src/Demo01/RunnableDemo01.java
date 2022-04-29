package Demo01;

public class RunnableDemo01 {
    public static void main(String[] args) {
        Runnable run = new RunnableImpl();
        Thread t = new Thread(run);
        t.start();

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName()+ "--" + i);
        }
    }
}
