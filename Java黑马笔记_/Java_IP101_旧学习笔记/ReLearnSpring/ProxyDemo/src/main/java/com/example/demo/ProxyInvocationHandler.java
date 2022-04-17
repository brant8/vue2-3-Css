package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//用这个类自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Rent rent;
    public void setRent(Rent rent){
        this.rent=rent;
    }

/** To create a proxy for some interface Foo:
    Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
        new Class<?>[] { Foo.class }, handler);
*/ //生成代理类
    public Object getProxy(){
       return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(),this);
    }
/**
public static Object newProxyInstance(ClassLoader loader,
                                  @NotNull Class<?>[] interfaces,
                                  @NotNull reflect.InvocationHandler h)
 */

    //处理代理实例，并返回结果（Processes a method invocation on a proxy instance and returns the result）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        //动态代理的本质就是使用反射机制实现
        Object result = method.invoke(rent, args);
        return result;
    }

    public void seeHouse(){
        System.out.println("中介带看房子");
    }
}
