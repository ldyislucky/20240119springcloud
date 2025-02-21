package com.ldy.config;

import com.ldy.aop.LogAspects;
import com.ldy.pointcut.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration//这个注解标记一个类为配置类
@EnableAspectJAutoProxy//启用Spring对AspectJ自动代理的支持。AspectJ是一个广泛使用的AOP框架
//@ComponentScan({"com.ldy.aop","com.ldy.show","com.ldy.pointcut"}) // 确保扫描到 A 类
public class AopConfig {
    // 将业务逻辑类（目标方法所在类）加入到容器中
    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    // 将切面类加入到容器中
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
