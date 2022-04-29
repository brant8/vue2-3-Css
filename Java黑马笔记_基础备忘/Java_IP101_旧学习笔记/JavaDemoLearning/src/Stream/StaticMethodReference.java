package Stream;

public class StaticMethodReference {
    public static int method(int number, Calcable c){
        return c.calAbs(number);
    }

    public static void main(String[] args) {
        int number = method(-10,(n)->{
            return Math.abs(n);
        });
        System.out.println(number);
    }
}
