package com.conan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * SpringBoot 项目的入口类，只需要直接运行该类或者java -jar xxxx.jar的方式启动项目即可
 */
@MapperScan("com.conan.demo.mapper")
@SpringBootApplication
// 默认指定的属性文件是 application.properties，可以通过该注解添加其它属性文件，注意是添加，application.properties始终默认加载
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
public class ApplicationDemo {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemo.class, args);
    }

}

