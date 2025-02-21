package com.ldy.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    public static void main(String[] param) {
        //生成动态代理类文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 目标对象
        Target target = new Target();
        /**
         *  生成动态代理类，动态代理类中含有
         *  被代理类的
         *  实现了接口方法的
         *  方法的
         *  方法对象、以及接口方法。
         */
        Foo proxy = (Foo) Proxy.newProxyInstance(
                //类加载器，在运行时加载动态生成的动态代理类
                Target.class.getClassLoader(),
                /**
                 * 类对象数组，动态代理类和被代理类要实现的接口的类对象；
                 * Proxy.newProxyInstance会通过接口的类对象和方法名获取方法对象作为动态代理类的静态变量
                 * 动态代理类要实现接口的方法
                 */
                new Class[]{Foo.class},
                /**
                 * lambda表达式省略的是new InvocationHandler（）.invoke(Object proxy, Method method, Object[] args)
                 * 3个参数分别为动态生成的代理类本身、要代理的方法对象、代理方法要传的参数；
                 * Proxy的成员变量   protected InvocationHandler h;
                 * Proxy有无参构造和传InvocationHandler构造
                 * 因为这一步只是生成动态代理类，不是调用动态代理类所以不需要实例化，调用动态代理类的方法的时候才需要实例化并
                 * 执行实现好好h.invoke的内容
                 */
                (p, method, args) -> {
                    //当调用动态代理类的方法的时候才会执行，被代理的所有方法都会这样执行
                    System.out.println("proxy before...");
                    Object result = method.invoke(target, args);
                    System.out.println("proxy after...");
                    return result;
                });
        /**
         * 实际上是回调 InvocationHandler
         *  h.invoke(this, foo, new Object[0]);
         *  就是执行上面的那些实现的代码
         */
        proxy.foo(1,2);
        System.out.println("==============");
        proxy.bar();

    }
}
