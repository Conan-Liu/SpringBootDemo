package com.conan.spring.ioc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
// 该注解表明该类需要被扫描装配进入Spring IoC容器，user是作为Bean的名称，可以不用配置该字符串，默认类名第一个字母小写后作为名称
// 不再需要配合@Bean注解
@Component("user_component")
// 设置作用域为Prototype，每次都是创建一个新的Bean返回，默认单例，返回同一个Bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User {

    // 该注解给属性注入对应的值
    @Value("10")
    private Long id;
    @Value("user_name_10")
    private String userName;
    @Value("note_10")
    private String note;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
