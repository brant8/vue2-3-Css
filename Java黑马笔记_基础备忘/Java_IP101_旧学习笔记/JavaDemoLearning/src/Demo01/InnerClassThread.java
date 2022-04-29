package Demo01;

public class InnerClassThread {
    public static void main(String[] args) {
        //new MyThread().start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName()+"-->"+"Thread");
                }
            }
        }.start();

        //Runnable r = new RunnableImpl;
        Runnable r = new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName()+"-->"+"Runnable");
                }
            }
        };
        new Thread(r).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName()+"-->"+"Runnable simplify");
                }
            }
        }).start();
    }
}
