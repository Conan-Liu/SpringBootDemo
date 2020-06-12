package com.conan.spring.ioc.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 默认指定的属性文件是 application.properties，可以通过该注解添加其它属性文件，注意是添加，application.properties始终默认加载
 *
 * @PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
 */
@Component
public class JdbcExp {

    // @Value 注解，使用占位符变量读取属性配置文件的内容
    @Value("${database.driverName}")
    private String driverName;

    @Value("${database.url}")
    private String url;

    private String userName;

    private String password;

    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        System.out.println(driverName);
        this.driverName = driverName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        System.out.println(url);
        this.url = url;
    }

    public String getUserName() {
        return this.userName;
    }

    @Value("${username}")
    public void setUserName(String userName) {
        System.out.println(userName);
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    @Value("${password}")
    public void setPassword(String password) {
        System.out.println(password);
        this.password = password;
    }

    public Long getInitTime() {
        return this.initTime;
    }

    public void setInitTime(Long initTime) {
        System.out.println(initTime);
        this.initTime = initTime;
    }

    @Override
    public String toString() {
        return "JdbcExp{" +
                "driverName='" + driverName + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", initTime=" + initTime +
                '}';
    }
}
