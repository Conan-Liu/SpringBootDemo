package com.conan.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 在高并发情况下，会出现库存为负数的场景，这是不合理的
 * 1. 悲观锁，库存是共享数据，对库存加锁，一个事务读取产品后，将数据直接锁定，不允许其它事务读写操作，可以解决并发问题，但是吞吐量减小，直接在sql语句后面加上 for update 即可
 * 2. 乐观锁，引入版本号vesion，只能递增，使用CAS机制，数据库增加一个version字段（只赠不减）
 */

@SpringBootApplication
@MapperScan(basePackages = "com.conan.shop.dao")
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    // 由于依赖过多，可以指定事务管理器，这里dataSource由Spring框架自动注入
    @Bean
    public PlatformTransactionManager injectTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
