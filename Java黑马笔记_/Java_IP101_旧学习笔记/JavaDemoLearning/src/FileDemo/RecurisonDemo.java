package FileDemo;

public class RecurisonDemo {
    public static void main(String[] args) {
        //a();
        System.out.println(sum(5));
    }

    private static int sum(int n) {
        if(n ==1 ){
            return 1;
        }
        return n+sum(n-1);
    }

    private static void a() {
        System.out.println("a method");
        a();
    }
}
