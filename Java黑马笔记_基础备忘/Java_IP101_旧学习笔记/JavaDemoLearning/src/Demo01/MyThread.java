package Demo01;

import org.jetbrains.annotations.NotNull;

public class MyThread extends Thread{
    public MyThread() {
    }

    public MyThread(@NotNull String name) {
        super(name);
    }

    //setup thread task
    public void run(){
//        Thread t = Thread.currentThread();
//        System.out.println(t);
        System.out.println(Thread.currentThread().getName());
    }
}
