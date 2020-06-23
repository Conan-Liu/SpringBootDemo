package com.conan.shop.dao;

import com.conan.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    /**
     * 如果Dao层只需要传递一个参数给Mapper.xml文件，那么只需要类型一致就行，没有歧义
     * 如果有多个参数，那么最好使用 @Param 注解显示标明要传入xml文件的变量
     */
    // 如变量名是id111，但是注解定义为id，那么xml文件执行使用id，而不是id111，这里要注意，一个参数没有歧义
    Product getProduct(@Param("id") Integer id111);

    // 如果是多个参数，那么需要显示使用 @Param注明
    int decreaseProduct(@Param("id") Integer id, @Param("quantity") int quantity, @Param("version") int version);
}
