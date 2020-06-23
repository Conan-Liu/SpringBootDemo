package com.conan.spring;

import com.conan.spring.aop.MyAspect;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 该类仅仅是作为om.conan.spring包中Spring学习的入口类，不是项目的的入口类
 * 4. mybatis和spring日志分离
 */
@SpringBootApplication(scanBasePackages = "com.conan.spring")
// 默认指定的属性文件是 application.properties，可以通过该注解添加其它属性文件，注意是添加，application.properties始终默认加载
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)

// 注册JPA信息，springboot中的JPA是依靠Hibernate实现的，可以在日志中看到打印的sql语句
// 定义JPA接口的扫描包路径
@EnableJpaRepositories(basePackages = "com.conan.spring.service")
// 定义实体Bean（Entity）扫描包路径
@EntityScan(basePackages = "com.conan.spring.pojo")
@MapperScan(basePackages = "com.conan.spring.dao") //, annotationClass = Repository.class)
// SpringBoot使用注解启动缓存机制
@EnableCaching
// 不用每次都定义LOG变量，直接使用
@Slf4j
public class ApplicationLearning implements WebMvcConfigurer{

    // 启动自定义的AOP切面
    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    /**
     * 测试多个切面
     * 同上初始化多个Aspect，启动即可
     */

    public static void main(String[] args) {
        log.info("this is lombok log4j {}",System.getProperty("user.name"));
        SpringApplication.run(ApplicationLearning.class, args);
    }

}

