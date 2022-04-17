package LambdaDemo;

import java.util.function.Consumer;

public class ConsumerDemo {

    public static void printInfo(String[] arr, Consumer<String> con1, Consumer<String> con2){
        for(String message: arr){
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"迪丽热巴,女","古力娜扎,女","马尔扎哈,男"};

        printInfo(arr,(message)->{
            String name = message.split(",")[0];
            System.out.print("Name: " + name);
        }, (message)->{
            String age = message.split(",")[1];
            System.out.print(",Age: " + age+".\n");
        });
    }
}
