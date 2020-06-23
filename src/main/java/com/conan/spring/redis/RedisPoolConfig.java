package com.conan.spring.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试
 */
@Configuration
public class RedisPoolConfig {

    private RedisConnectionFactory connectionFactory;

    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null) {
            return this.connectionFactory;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(30);
        // 最大连接数
        poolConfig.setMaxIdle(50);
        // 最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        // 创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        // 获取单机Redis
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        rsCfg.setHostName("localhost");
        rsCfg.setPort(6379);
        rsCfg.setPassword("123456");
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }

    /**
     * RedisTemplate提供常用的两种序列化类
     * {@link org.springframework.data.redis.serializer.JdkSerializationRedisSerializer} 默认的序列化类，不过序列化的内容可读性不高
     * {@link org.springframework.data.redis.serializer.StringRedisSerializer} 字符串形式保存，便于查看，调整为这个序列化类
     * 如果只需要处理String类型的数据，那么只需要{@link org.springframework.data.redis.core.StringRedisTemplate}即可
     */
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer stringSerializer=redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
