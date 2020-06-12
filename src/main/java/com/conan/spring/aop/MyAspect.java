package com.conan.spring.aop;

import org.aspectj.lang.annotation.*;

/**
 * 开发切面
 * 1. 前置通知
 * 2. 后置通知
 * 3. 返回通知
 * 4. 异常通知
 * 切面主要作用在类的方法上，让增则表达式对应的方法植入AOP流程
 *
 * 这里简单点理解，就是约定代码执行的流程顺序，before -> 连接点方法 -> after -> afterReturning（如果异常则执行afterThrowing）
 * 连接点方法是用户写代码逻辑的方法，其它三个就是切面方法，用户想要执行连接点方法的时候，spring框架会按这个顺序执行完才返回
 */
// 该注解作为切面的声明，Spring会知道这是一个切面
@Aspect
public class MyAspect {

    /**
     * 如下@Before,@After,@AfterReturning,@AfterThrowing四个注解对应同一个正则表达式，略显冗余，从而有了切点（Pointcut）概念
     * 切点的主要作用是向Spring描述哪些类的哪些方法需要启用AOP编程
     */
    @Pointcut("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    public void pointCut(){

    }

    // 启用了切点，可以如下简写，直接引用切点即可
    // @Before("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @Before("pointCut()")
    public void before(){
        System.out.println("before ......");
    }

    // @After("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @After("pointCut()")
    public void after(){
        System.out.println("after ......");
    }

    // @AfterReturning("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning ......");
    }

    // @AfterThrowing("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing ......");
    }
}
