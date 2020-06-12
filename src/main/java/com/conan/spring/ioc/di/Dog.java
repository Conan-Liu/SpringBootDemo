package com.conan.spring.ioc.di;

import org.springframework.stereotype.Component;

// Animal的一种实现，把该Dog对象装配到IoC容器中
@Component
public class Dog implements Animal{

    @Override
    public void use() {
        System.out.println("狗 ["+Dog.class.getSimpleName()+"] 可以看门...");
    }
}
