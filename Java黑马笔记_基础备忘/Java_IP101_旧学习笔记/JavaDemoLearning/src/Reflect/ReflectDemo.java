package Reflect;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class cls1 = Class.forName("Reflect.Person");
        System.out.println(cls1);

        Class cls2 = Person.class;
        System.out.println(cls2);

        Person p = new Person();
        Class cls3 = p.getClass();
        System.out.println(cls3);

        //compare three objects
        System.out.println(cls1 == cls2);
        System.out.println(cls3 == cls2);


    }
}
