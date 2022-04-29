package LambdaDemo;

public class DemoLogger {
    public  static void showLog(int level, String message){
        if(level == 1){
            System.out.println(message);
        }
    }

    public static void main(String[] args){
        String msg1 = "Hello";
        String msg2 = "World";
        String msg3 = "Java";

        long t1 = System.currentTimeMillis();

        showLog(2,msg1+msg2+msg3);

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }

}
