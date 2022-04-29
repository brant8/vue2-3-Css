package com.example.demo;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ReflectionDemo {

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取Class对象的三种方式：
        //1.通过公有属性class获取Class对象：通过类获取，无需创建类对象
        Class user = User.class;
        System.out.println(user+" 和Hash code："+user.hashCode());
        //2.通过方法getClass()获取Class对象：需要类的对象。常用于不知道类名但是能获取对象的情况下。
        User user1 = new User();
        Class c1 = user1.getClass();
        System.out.println(c1+" 和Hash code："+c1.hashCode());
        //3.通过方法Class.forName()获取Class对象：需要类的全名，需抛出异常。常用于加载配置。
        Class c2 = Class.forName("com.example.demo.User");
        System.out.println(c2+" 和Hash code："+c2.hashCode());

/** 通过反射实例化对象的两种方式
        Class.newInstance()
        Constructor.newInstance()
*/      Object o = user.getDeclaredConstructor().newInstance();
        Object o1 = c1.newInstance();
        System.out.println(o);
        System.out.println(o1);
    }
}
