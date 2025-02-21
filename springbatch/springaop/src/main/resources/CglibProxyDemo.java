package com.ldy.proxy.cglib;




public class CglibProxyDemo {

    static class Target {
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] param) {
        // 目标对象
        Target target = new Target();
        // 代理对象
        Target proxy = (Target) Enhancer.create(Target.class,
                (MethodInterceptor) (p, method, args, methodProxy) -> {
                    System.out.println("proxy before...");
                    Object result = methodProxy.invoke(target, args);
                    // 另一种调用方法，不需要目标对象实例
//            Object result = methodProxy.invokeSuper(p, args);
                    System.out.println("proxy after...");
                    return result;
                });
        // 调用代理
        proxy.foo();
    }
}