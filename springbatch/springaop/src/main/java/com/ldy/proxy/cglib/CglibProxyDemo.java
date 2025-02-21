package com.ldy.proxy.cglib;

/**
 * 不能在spring中运行，想要在spring中运行需要修改配置
 */

/**

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibProxyDemo {
    static class Target {
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] param) {
        // 目标对象
        Target target = new Target();

        // 创建Enhancer实例并配置参数
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);          // 设置代理类的父类
        enhancer.setCallback((MethodInterceptor) (p, method, args, methodProxy) -> {
            System.out.println("proxy before...");
            // 调用目标对象的方法（需传入实际目标实例）
            Object result = methodProxy.invoke(target, args);
            // 或使用invokeSuper（无需目标实例，但需通过代理对象调用）
            // Object result = methodProxy.invokeSuper(p, args);
            System.out.println("proxy after...");
            return result;
        });

        // 生成代理对象
        Target proxy = (Target) enhancer.create();

        // 调用代理
        proxy.foo();
    }
}
*/