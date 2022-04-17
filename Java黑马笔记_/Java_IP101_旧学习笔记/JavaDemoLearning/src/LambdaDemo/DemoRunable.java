package LambdaDemo;

import com.sun.security.jgss.GSSUtil;

public class DemoRunable {
    public static void main(String[] args) {
        RunableImpl run = new RunableImpl();
        Thread t = new Thread(run);
        t.start();

        Runnable rn = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        new Thread(rn).start();

        //使用lambda表达式
        new Thread(()->{
                System.out.println(Thread.currentThread().getName());

        }).start();
    }
}
