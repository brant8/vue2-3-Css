package LambdaDemo;

public class Demo {
    public static void show(MyFunctionalInterface myInter){
        myInter.method();
    }

    public static void main(String[] args) {
        show(new MyFunctionalInterfaceImpl());

        show(new MyFunctionalInterface() {
            @Override
            public void method() {
                System.out.println("Anonymous abstract method.");
            }
        });

        show(()-> System.out.println("use Lambda expression rewrite method."));

    }
}
