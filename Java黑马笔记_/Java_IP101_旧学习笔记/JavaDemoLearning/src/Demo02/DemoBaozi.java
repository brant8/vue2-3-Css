package Demo02;

public class DemoBaozi {
    public static void main(String[] args) {
        BaoZi bz = new BaoZi();
        //开始生产包子
        new BaoZiPu(bz).start();

        new ChiHuo(bz).start();
    }
}
