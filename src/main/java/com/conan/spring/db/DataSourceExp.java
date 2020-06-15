package com.conan.spring.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * ApplicationContextAware Spring Boot初始化时会自动调用
 *
 * 通过 spring.datasource.type 指定DataSource类型
 */
@Component
public class DataSourceExp implements ApplicationContextAware{

    ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
        // 获取Spring IoC容器中DataSource的类型
        DataSource dataSource=this.context.getBean(DataSource.class);
        System.out.println("IoC容器中的DataSource : "+dataSource.getClass().getName());
    }
}
