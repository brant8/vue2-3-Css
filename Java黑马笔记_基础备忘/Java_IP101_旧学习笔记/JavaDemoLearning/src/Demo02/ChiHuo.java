package Demo02;

public class ChiHuo extends Thread{
    //同步技术保证两个线程只能有一个执行
    private BaoZi bz;

    public ChiHuo(BaoZi bz){
        this.bz = bz;
    }

    @Override
    public void run() {
        while (true){
            synchronized(bz){
                if (bz.flag==false){
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("吃货正在吃"+ bz.pi + bz.xian + "包子");

                bz.flag=false;
                bz.notify();
                System.out.println("吃货已经吃完"+ bz.pi + bz.xian + "包子，需要重新生产包子");
            }
        }
    }
}
