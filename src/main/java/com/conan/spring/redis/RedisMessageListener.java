package com.conan.spring.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Redis 发布订阅模式
 * 定义一个消息监听器，来监听Redis渠道发过来的消息
 */
@Component
public class RedisMessageListener implements MessageListener{

    /**
     * 监听器得到消息后的处理方法
     * message    消息体
     * pattern    渠道名称
     */
    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        // 消息体
        String body=new String(message.getBody());
        // 渠道名称
        String topic=new String(pattern);
        System.out.println(body);
        System.out.println(topic);
    }
}
