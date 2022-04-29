package LambdaDemo;

import java.util.function.Function;

public class Function_andThen {

    public  static void change(String s ,Function<String,Integer> fun1, Function<Integer,String> fun2){
        String ss = fun1.andThen(fun2).apply(s);
        System.out.println(ss);
    }
    public static void main(String[] args) {
        String s = "123";
        change(s,(String str)->{
            return Integer.parseInt(str)+10;
        },(Integer i)->{
            return i+"";
        });

        String str2 = "zhao,20";
        int ii = change2(str2,(String s2)->{
            return s2.split(",")[1];
        },(String s2)->{
            return Integer.parseInt(s2);
        },(Integer i)->{
            return i+100;
        });
        System.out.println(ii);
    }
    //Function<String,String>,  Function<String,Integer>,   Function<Integer,Integer>
    public static int change2(String s, Function<String,String> fun1, Function<String,Integer> fun2, Function<Integer,Integer> fun3){
        //andThen
        return fun1.andThen(fun2).andThen(fun3).apply(s);
    }
}
