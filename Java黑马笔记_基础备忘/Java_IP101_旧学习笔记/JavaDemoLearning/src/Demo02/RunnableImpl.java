package Demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableImpl implements Runnable{
    //
    private int ticket = 100;
    //create lock object
    Object obj = new Object();

    Lock l = new ReentrantLock();

    @Override
    public void run() {
        System.out.println("Impl this: " + this);
        while(true) {
            payTicket();
        }
    }

    public synchronized void payTicket(){
            l.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Selling No. " + ticket + " Ticket.");
                ticket--;
            }
            l.unlock();
    }
}
