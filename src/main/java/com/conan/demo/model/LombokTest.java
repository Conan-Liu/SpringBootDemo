package com.conan.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Conan on 2019/6/24.
 */
// 这个可以全局注解这个类的getter和setter方法
@Getter
@Setter
// 这个注解是初始化一个 log 日志对象，可以直接调用这个变量
@Slf4j
public class LombokTest {

    // 这个只会针对性的注解某个字段的getter和setter方法
    @Setter
    @Getter
    private String name;
    private Integer age;
    private Double height;

    public static void main(String[] args) {
        LombokTest t=new LombokTest();
        t.setName("hahaha");

        log.info("这是注解引入的日志对象收集");
    }

}