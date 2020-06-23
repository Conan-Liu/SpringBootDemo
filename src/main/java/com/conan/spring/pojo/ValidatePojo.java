package com.conan.spring.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 通过注解的方式对数据进行验证合法性和准确性
 *
 * 也可以实现接口{@link org.springframework.validation.Validator}来自定义验证
 */
@Data
public class ValidatePojo {

    // 注意这里要测试id不为空，一定要Integer类型，int类型默认0，就算不传入，也不会被判定为Null
    @NotNull(message = "id 不能为空")
    private Integer id;

    @Future(message = "需要一个未来日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @DecimalMin(value = "0.1") // 最小值
    @DecimalMax(value = "10000.00") // 最大值
    private double doubleValue;

    @Range(min = 1, max = 888, message = "范围为1至888") // 限定范围
    private long range;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 4, max = 8, message = "字符串的长度限制4到8之间")
    private String size;
}
