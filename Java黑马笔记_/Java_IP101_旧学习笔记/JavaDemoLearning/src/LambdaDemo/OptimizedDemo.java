package LambdaDemo;

import java.util.Date;

public class OptimizedDemo {
    public static void showLog(int level, MessageBuilder mb){
        if(level ==1){
            System.out.println(mb.builderMessage());
        }
    }

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = "World";
        String msg3 = "Java";
        long t1 = System.currentTimeMillis();

        showLog(1,()->{
            System.out.println("not satisfied condition...");
            return msg1+msg2+msg3;
        });

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }
}
