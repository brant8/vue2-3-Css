package Demo02;

public class WaitAndNotify02 {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(){
            @Override
            public void run() {
                //waiting time and notify only have one
                synchronized(obj){
                    System.out.println("TEll Boss to make Baozi...");
                    try {
                        obj.wait(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //after notify
                    System.out.println("eating Baozis");
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("I am Boss ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj){
                    System.out.println("Baozi finished.");
                    obj.notify();
                }
            }
        }.start();
    }
}
