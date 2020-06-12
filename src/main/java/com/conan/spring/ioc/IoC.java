package com.conan.spring.ioc;

import com.conan.spring.ioc.property.JdbcExp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * IoC 控制反转
 * 对象的创建不再需要程序员手动new，而是交给Spring IoC容器来创建
 */
public class IoC {

    public static void main(String[] args) {
        // 根据自定义的Spring Configuration配置文件来构建容器
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 根据Bean名称和类型来获取对象
        User user1 = context.getBean("user_component", User.class);
        System.out.println(user1);

        User user2 = context.getBean("user_component", User.class);
        System.out.println("is singleton: " + (user1 == user2));  // true 可以看到IoC容器Bean是单例的，两个变量指向同一个Bean，当设置为prototype时，返回false


        JdbcExp jdbcExp=context.getBean("jdbcExp",JdbcExp.class);
        System.out.println(jdbcExp);
    }
}
