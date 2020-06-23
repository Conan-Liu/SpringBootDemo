package com.conan.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis的配置装配Bean
 */
@Configuration
public class RedisConfig {

    /**
     * Redis为了更好的扩展性，使用RedisTemplate，序列化使用StringRedisSerializer，这样Redis就可以使用字符串存储
     * 这里需要等SpringBoot启动的时候才能完成装配，所以此处redisTemplate变量可能会报no beans of的错，调整下Idea的Inspection提示即可
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // spring不推荐属性字段直接注入，推荐通过构造方法注入
    @Autowired
    public RedisConfig(RedisTemplate<Object,Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @Bean
    public void initRedisTemplate() {
        // 指定redis数据库
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        connectionFactory.getStandaloneConfiguration().setDatabase(2);
        // 指定Spring使用的Redis序列化类
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

}
