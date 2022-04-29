package Reflect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCheck {
//steps to auto-check code
    public static void main(String[] args) throws IOException {
        //1. Create object
        Calculator c = new Calculator();
        //2. Get output byte object
        Class cls = c.getClass();
        //3.Get all methods
        Method[] methods = cls.getMethods();

        int number = 0;
        BufferedWriter bw = new BufferedWriter((new FileWriter("bug.txt")));

        for(Method method: methods){
            //check if methods have @Check
            if(method.isAnnotationPresent(Check.class)){
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    number++;

                    bw.write(method.getName()+" Method have Exception..");
                    bw.newLine();
                    bw.write("Exception is: " + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("Exception Reason is: " + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("-------------");
                    bw.newLine();
                }
            }
        }
        //4.Invoke method if include @Check
        //5.If do, execute
        bw.write("total have "+ number +" Exception");

        bw.close();
        //6catch Exception
    }
}
