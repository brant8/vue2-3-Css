package LambdaDemo;

public class CalculatorDemo {
    public static void main(String[] args) {
        invokeCalc(1, 12, new Calculator() {
            @Override
            public int cal(int a, int b) {
                return a+b;
            }
        });

        invokeCalc(1,21,(int a, int b)->{
            return a+b;
        });
    }
    public static void invokeCalc(int a, int b, Calculator c){
        int sum = c.cal(a,b);
        System.out.println(sum);
    }
}
