package com.conan.spring.aop;

import com.conan.spring.ioc.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 开发切面
 * 1. 前置通知
 * 2. 后置通知
 * 3. 返回通知
 * 4. 异常通知
 * 切面主要作用在类的方法上，让增则表达式对应的方法植入AOP流程
 * <p>
 * 这里简单点理解，就是约定代码执行的流程顺序，before -> 连接点方法 -> after -> afterReturning（如果异常则执行afterThrowing）
 * 连接点方法是用户写代码逻辑的方法，其它三个就是切面方法，用户想要执行连接点方法的时候，spring框架会按这个顺序执行完才返回
 */
// 该注解作为切面的声明，Spring会知道这是一个切面
@Aspect
public class MyAspect {

    // 使用AOP实现一个简单的日志切面
    private static final Logger LOG= LoggerFactory.getLogger(MyAspect.class);

    /**
     * 如下@Before,@After,@AfterReturning,@AfterThrowing四个注解对应同一个正则表达式，略显冗余，从而有了切点（Pointcut）概念
     * 切点的主要作用是向Spring描述哪些类的哪些方法需要启用AOP编程
     */
    @Pointcut("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

    // 启用了切点，可以如下简写，直接引用切点即可
    // @Before("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    // 在通知中传递参数，args 把名称为user的参数传递进来
    @Before("pointCut() && args(user)")
    public void before(JoinPoint point, User user) {
        System.out.println("before ...... = " + user);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        LOG.info("请求地址:{}",request.getRequestURL().toString());
        LOG.info("ip:{}",request.getRemoteAddr());

    }

    // @After("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    // @AfterReturning("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    // @AfterThrowing("execution(* com.conan.spring.aop.UserServiceImpl.printUser(..))")
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }

    // 环绕通知，很强大，但是没必要的时候，尽量不要使用
    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before ......");
        jp.proceed();
        System.out.println("around after ......");
    }
}






/**
 * 下面三个类用来测试多个切面
 * 切面的执行是无序的，可以通过@Order注解来指定顺序
 */

@Aspect
@Order(1)
class MyAspect1{

    @Pointcut("execution(* com.conan.spring.aop.UserServiceImpl.manyAspects(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println("MyAspect1 before ......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("MyAspect1 after ......");
    }

    @After("pointCut()")
    public void afterReturning(){
        System.out.println("MyAspect1 afterReturning ......");
    }
}


@Aspect
@Order(2)
class MyAspect2{

    @Pointcut("execution(* com.conan.spring.aop.UserServiceImpl.manyAspects(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println("MyAspect2 before ......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("MyAspect2 after ......");
    }

    @After("pointCut()")
    public void afterReturning(){
        System.out.println("MyAspect2 afterReturning ......");
    }
}


@Aspect
@Order(3)
class MyAspect3{

    @Pointcut("execution(* com.conan.spring.aop.UserServiceImpl.manyAspects(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println("MyAspect3 before ......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("MyAspect3 after ......");
    }

    @After("pointCut()")
    public void afterReturning(){
        System.out.println("MyAspect3 afterReturning ......");
    }
}
