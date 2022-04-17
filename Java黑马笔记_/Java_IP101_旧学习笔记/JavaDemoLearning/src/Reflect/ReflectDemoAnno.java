package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Pro(className = "Reflect.Demo1",methodName = "show")
public class ReflectDemoAnno {
    public static void main(String[] args) throws Exception{
        //Get file byte object
        Class<ReflectDemoAnno> reflect = ReflectDemoAnno.class;
        //get annotation object
        Pro an = reflect.getAnnotation(Pro.class);
        String className = an.className();
        String methodName = an.methodName();

        System.out.println(className);
        System.out.println(methodName);

        Class cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(obj);
    }
}
