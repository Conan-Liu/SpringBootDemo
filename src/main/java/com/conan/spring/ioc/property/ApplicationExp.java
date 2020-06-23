package com.conan.spring.ioc.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 注意：这种占位符的表示方式就是 Spring EL
 */

@Component
// 该注解会查找属性文件变量，这里指定前缀，POJO属性变量名和属性文件变量的后缀相同，则自动注入
// 还要注意到，这里setTwo方法也加上了注解，所以会打印两次
@ConfigurationProperties("day")
public class ApplicationExp {

    // 使用@Value注解，应用在只读不可修改的场景，如读取配置文件变量，注入到对象成员变量，驱动类用户名密码变量都可以如此
    @Value("${day.one}")
    private String one;

    private String two;

    private String three;

    // 该类有@ConfigurationProperties注解，所以这里不起作用
    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;

    public String getOne() {
        return this.one;
    }

    public void setOne(String one) {
        System.out.println(one);
        this.one = one;
    }

    public String getTwo() {
        return this.two;
    }

    @Value("${day.two}")
    public void setTwo(String two) {
        System.out.println(two);
        this.two = two;
    }

    public String getThree() {
        return this.three;
    }

    public void setThree(String three) {
        System.out.println(three);
        this.three = three;
    }

    public Long getInitTime() {
        return this.initTime;
    }

    public void setInitTime(Long initTime) {
        System.out.println(initTime);
        this.initTime = initTime;
    }
}
