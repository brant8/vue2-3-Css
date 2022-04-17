package LambdaDemo;

import java.util.function.Supplier;

public class SupplierDemo {
    public static String getString(Supplier<String> sup){
        return sup.get();
    }

    public static void main(String[] args) {
        String s = getString(()->{
            return "String returns...";
        });

        System.out.println(s);
    }
}
