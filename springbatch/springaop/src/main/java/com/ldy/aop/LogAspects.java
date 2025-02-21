package com.ldy.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类
 *
 * @Aspect：告诉Spring当前类是一个切面类，而不是一些其他普通的类
 * @author liayun
 *
 */
@Component
@Aspect
public class LogAspects {

    // 如果切入点表达式都一样的情况下，那么我们可以抽取出一个公共的切入点表达式
    @Pointcut("execution(public int com.ldy.pointcut.MathCalculator.div(int ,int ))")//方法的传参不能被省略
    public void pointCut() {}

    // @Before：在目标方法（即div方法）运行之前切入，public int com.meimeixia.aop.MathCalculator.div(int, int)这一串就是切入点表达式，指定在哪个方法切入
    // @Before("public int com.meimeixia.aop.MathCalculator.*(..)")
    @Before("pointCut()")
    public void logStart() {
        System.out.println("除法运行......@Before，参数列表是：{}");
    }

    // 在目标方法（即div方法）结束时被调用
    // @After("pointCut()")
    @After("com.ldy.aop.LogAspects.pointCut()")
    public void logEnd() {
        System.out.println("除法结束......@After");
    }

    // 在目标方法（即div方法）正常返回了，有返回值，被调用
    @AfterReturning("pointCut()")
    public void logReturn() {
        System.out.println("除法正常返回......@AfterReturning，运行结果是：{}");
    }

    // 在目标方法（即div方法）出现异常，被调用
    @AfterThrowing("pointCut()")
    public void logException() {
        System.out.println("除法出现异常......异常信息：{}");
    }

}

