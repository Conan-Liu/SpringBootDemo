package com.conan.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

// 注解为Spring的配置文件
@Configuration
// 扫描包，默认情况下该注解只会扫描该类所在当前包和其子包，可以通过basePackages指定路径，这里配置了会过滤掉@Service注解的类，不装配到IoC容器
@ComponentScan(basePackages = "com.conan.demo.spring", excludeFilters = @ComponentScan.Filter(classes = Service.class))
public class AppConfig {

    // 注解方法，使方法返回的POJO对象自动装配到IoC容器中，name属性为对象名称
    @Bean(name = "user")
    public User initUser() {
        // 在使用@Value注解的情况下，下面的属性设置值无效
        User user = new User();
        user.setId(1L);
        user.setUserName("userName1");
        user.setNote("note1");
        return user;
    }

    // @Bean注解可以引入第三方jar包的类，装配入IoC，如下为例子
    // @Bean(name = "jdbc_connection")
    public Connection getJdbcDriver() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 开发过程可能有测试和生产环境的切换，可以使用@Profile注解来实现
     * 还需要启动的时候传入参数，来指定需要哪种配置
     * -Dspring.profiles.active=${profile}
     * Spring 默认会用 application-${profile}.properties 去代替原来默认的 application.properties
     */
    /*
    @Bean(name = "dataSource", destroyMethod = "close")
    @Profile("dev")
    public DataSource getDevDataSource() {
        return null;
    }

    @Bean(name = "dataSource", destroyMethod = "close")
    @Profile("prod")
    public DataSource getProdDataSource() {
        return null;
    }
    */
}
