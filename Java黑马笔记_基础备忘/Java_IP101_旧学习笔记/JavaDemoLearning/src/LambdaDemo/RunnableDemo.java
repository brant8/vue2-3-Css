package LambdaDemo;

public class RunnableDemo {
    public static void startThread(Runnable run){
        new Thread(run).start();
    }

    public static void main(String[] args) {
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ "--> Thread started..");
            }
        });

        startThread(()->{
            System.out.println(Thread.currentThread().getName()+ "--> Thread started..");
        });

    }
}
