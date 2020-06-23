package com.conan.spring.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * RedisTemplate提供每种数据类型的操作接口，分别对应7中数据类型，通过操作接口来操作不同的数据类型，一个连接执行一条命令
 * redisTemplate.opsForValue()    String字符串
 * redisTemplate.opsForHash()     Hash散列
 * redisTemplate.opsForList()     List列表
 * redisTemplate.opsForSet()      Set集合
 * redisTemplate.opsForZSet()     SortedSet有序集合
 * redisTemplate.opsForHyperLogLog()  基数
 * redisTemplate.opsForGeo()          Geo地理位置
 * <p>
 * 还提供一个连接执行多个Redis命令
 * SessionCallback   良好的封装，开发者友好，优先选择
 * RedisCallback     接口比较底层，处理的内容也多，可读性差
 */

public class RedisExp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisPoolConfig.class);
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        // 执行后查看redis，发现几乎不可读，RedisTemplate使用默认的JdkSerializationRedisSerializer来序列化
        // 希望Redis以普通字符串来保存，需要修改序列化类为 StringRedisSerializer
        redisTemplate.opsForValue().set("key2", "value1");
        redisTemplate.opsForHash().put("hash", "field", "hvalue");
        System.out.println(redisTemplate.getDefaultSerializer());


    }

    public void userRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set("key1".getBytes(), "value1".getBytes());
                connection.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
                return null;
            }
        });
    }

    // SessionCallback 内部可以直接使用普通的操作接口，更直观
    public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("key1", "value1");
                operations.opsForHash().put("hash", "field", "hvalue");
                return null;
            }
        });
    }

}
