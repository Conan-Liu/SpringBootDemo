package com.conan.spring.pojo.jpa;

import com.conan.spring.pojo.SexEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * JPA 的实体类
 */
// 标明是一个实体类
@Entity(name = "user")
// 定义映射的数据库表
@Table(name = "t_user")
@Data
public class User {

    // 标明主键
    @Id
    // 主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 定义属性和表的映射关系，因为属性名称和数据库列名不一致，需要显示指定
    @Column(name = "user_name")
    private String userName;

    // 定义转换器，性别需要特殊转换，因为数据库中的值不是我们想要的，需要转成枚举值
    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    private String note;
}
