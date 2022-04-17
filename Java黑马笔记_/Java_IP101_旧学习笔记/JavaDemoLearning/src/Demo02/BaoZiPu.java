package Demo02;

public class BaoZiPu extends  Thread{
    //同步技术保证两个线程只能有一个执行
    private BaoZi bz;

    public BaoZiPu(BaoZi bz){
        this.bz = bz;
    }

    //produce Baozi
    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (bz) {
                if (bz.flag == true) {
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //after notify
                if (count % 2 == 0) {
                    bz.pi = "薄皮";
                    bz.xian = "三鲜馅";
                } else {
                    bz.pi = "冰皮";
                    bz.xian = "牛肉大葱";
                }
                count++;
                System.out.println("包子铺正在生产： " + bz.pi + bz.xian + "包子");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //生产好包子后
                bz.flag = true;
                bz.notify();
                System.out.println("包子已经生产好了" + bz.pi + bz.xian + "包子，可以开始吃了");
            }
        }
    }
}
