package Demo02;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.submit(new PoolRunnableImpl());
        es.submit(new PoolRunnableImpl());
        es.submit(new PoolRunnableImpl());
        es.submit(new PoolRunnableImpl());
        es.submit(new PoolRunnableImpl());
        es.submit(new PoolRunnableImpl());
    }
}
