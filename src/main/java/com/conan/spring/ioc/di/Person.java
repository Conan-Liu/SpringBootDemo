package com.conan.spring.ioc.di;

/**
 * 演示依赖注入
 * 人类可以通过动物提供一些特殊服务，如让猫抓老鼠，狗看门，等等
 */
public interface Person {

    // 使用动物服务
    void service();

    // 设置动物
    void setAnimal(Animal animal);
}
