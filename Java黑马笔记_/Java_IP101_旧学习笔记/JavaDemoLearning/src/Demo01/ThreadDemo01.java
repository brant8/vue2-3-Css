package Demo01;

public class ThreadDemo01 {

    public static void main(String[] args) {
        MyThread td = new MyThread();
        td.setName("xiaoqiang");
        td.start();
        new MyThread("daqiang").start();
        System.out.println("---");
        System.out.println(Thread.currentThread().getName());
    }
}
