package com.conan.spring.ioc.di;

import com.conan.spring.ioc.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DI {

    public static void main(String[] args) {
        // 初始化Spring IoC的容器
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        Person person=context.getBean("businessPerson",BusinessPerson.class);
        person.service();
    }
}
