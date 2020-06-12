package com.conan.spring.ioc.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BusinessPerson implements Person {

    /**
     * 自动装配，根据属性对象的类型（by type）在IoC容器中找到对应的Bean进行注入
     * 如果只装配了Dog一个对象，那么可以直接从容器中获取并注入，继续增加一个Cat对象，两者都是Animal的实现，就会出现无法确定应该装配哪个对象的歧义
     * 1. 该注解装配顺序，先是查找类型，无法区别的情况下，再根据属性名称（变量名）和Bean的名称匹配，所以这里可以把变量名称改成和容器中的Bean名称一致，如dog或cat
     * 2. 第1种方法显然不够灵活，可以考虑@Primary注解，表明该类的Bean是优先考虑注入，如果多个Bean都加上该注解，还是会歧义
     * 3. 推荐该方法，使用@Qualifier，指定Bean的名称，唯一表示Bean，消除歧义
     * 该注解默认是必须要在IoC容器中找到对应的Bean，否则会报错，如果不确定容器中是否有对应的Bean或者允许为null，则可以使用required属性，当然有可能会报空指针异常
     * 可以标注方法
     */
    @Autowired(required = false)
    @Qualifier(value = "dog")
    private Animal animal = null;

    private Animal animal1 = null;

    // 构造方法里面使用注解实现依赖注入
    public BusinessPerson(@Autowired @Qualifier("cat") Animal animal) {
        this.animal1 = animal;
    }

    @Override
    public void service() {
        this.animal.use();
        this.animal1.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
