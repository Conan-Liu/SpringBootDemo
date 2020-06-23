package com.conan.spring.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.conan.spring.component.InterceptorExp;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 专门用来装配Bean，完成初始化的一些配置
 */
@Configuration
public class BasicConfig implements WebMvcConfigurer {

    // Spring自带的环境变量
    @Autowired
    private Environment env;

    /**
     * 测试事务管理器，这里使用JDBC版本的{@link org.springframework.jdbc.datasource.DataSourceTransactionManager}
     */
    // 注入事务管理器，SpringBoot自动生成
    @Autowired
    private PlatformTransactionManager transactionManager;

    // 使用后初始化方法，观察自动生成的事务管理器，使用@Bean注解，Spring框架执行该方法
    @Bean
    public Object viewTransactionManager() {
        System.out.println(">>>>>>>>>>>>>   "+env.getProperty("day.one","hahaha"));
        System.out.println(">>>>>>>>>>>>>   " + transactionManager.getClass().getName());
        return new Object();
    }

    // 将datasource装配入IoC容器，SpringBoot自动完成装配
    // @Bean
    public DataSource injectDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("");
        basicDataSource.setUsername("");
        basicDataSource.setPassword("");
        return basicDataSource;
    }

    // 由于依赖过多，可以指定事务管理器，这里dataSource由Spring框架自动注入
    @Bean
    public PlatformTransactionManager injectTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    // 实现WebMvcConfigurer，注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new InterceptorExp());
        // 这里添加了正则表达式匹配请求，所以只会拦截/interceptor开头的请求，例子：/hello/interceptor/start
        ir.addPathPatterns("/*/interceptor/*");

        // 可以如上格式定义多个拦截器
    }

    // 注册fastjson为消息转换器，@Bean注解将放回对象放入IoC容器即可覆盖默认，SpringBoot默认的是MappingJackson2HttpMessageConverter，一般情况下不用调整
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 需要定义一个convert转换消息对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置消息
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 处理中文乱码
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // 在convert中添加配置信息
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }
}
