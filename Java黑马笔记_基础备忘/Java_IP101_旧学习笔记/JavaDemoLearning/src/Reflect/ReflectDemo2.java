package Reflect;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        Class personClass = Person.class;

        Method eat_method = personClass.getMethod("eat",String.class);
        Person pp = new Person();
        eat_method.invoke(pp,"haha");
        Method[] methods = personClass.getMethods();
        for(Method m : methods){
            //System.out.println(m);
            System.out.println(m.getName());
        }




        System.out.println("\n******************************\n");

        Constructor con = personClass.getConstructor(String.class,int.class);
        System.out.println(con);
        Object person1 = con.newInstance("zhang", 12);
        System.out.println(person1);

        System.out.println("\n******************************\n");
        Field[] fields = personClass.getFields();
        for(Field f : fields){
            System.out.println(f);
        }
        System.out.println("-------------");
        Field a = personClass.getField("a");
        System.out.println(a);
        Person p = new Person();
        Object value = a.get(p);
        System.out.println(value);
        a.set(p,123);
        System.out.println(p);
        System.out.println("-------------");

        Field[] declaredFields = personClass.getDeclaredFields();
        for(Field f:declaredFields){
            System.out.println(f);
        }
        Field declaredField = personClass.getDeclaredField("age");
        declaredField.setAccessible(true);
        Object value3 = declaredField.get(p);
        declaredField.set(p,1233);
        System.out.println(value3);
        System.out.println(p);
    }
}
