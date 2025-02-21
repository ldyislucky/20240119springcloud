package com.ldy.proxy.jdk;

public class Target implements Foo {


    public void foo(int a, int b) {
        System.out.println("target foo " + a +b);
    }

    public int bar() {
        System.out.println("target bar");
        return 100;
    }
    //接口中没有这个方法所以生成的动态代理类中也没有这个方法
    public int test(){
        System.out.println("target test");
        return 0;
    }
}
