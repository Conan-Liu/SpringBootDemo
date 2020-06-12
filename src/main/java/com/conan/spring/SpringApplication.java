package com.conan.spring;

import com.conan.spring.aop.MyAspect;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 该类仅仅是作为om.conan.spring包中Spring学习的入口类，不是项目的的入口类
 */
@SpringBootApplication(scanBasePackages = "com.conan.spring")
// 默认指定的属性文件是 application.properties，可以通过该注解添加其它属性文件，注意是添加，application.properties始终默认加载
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
public class SpringApplication {

    // 启动自定义的AOP切面
    @Bean(name="myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

}

