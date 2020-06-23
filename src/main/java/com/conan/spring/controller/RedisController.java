package com.conan.spring.controller;

import com.conan.spring.pojo.redis.User;
import com.conan.spring.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

    // Spring框架提供注入，可以直接使用，这里测试两个Template
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserRedisService userRedisService;

    @RequestMapping(value = "/insertstringandhash")
    @ResponseBody
    public Map<String, Object> insertStringAndHash() {
        redisTemplate.opsForValue().set("c_key1", "value1");
        redisTemplate.opsForValue().set("int_key1", "1");

        stringRedisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().increment("int", 1);

        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");

        stringRedisTemplate.opsForHash().putAll("c_hash", hash);
        stringRedisTemplate.opsForHash().put("c_hash", "field3", "value3");

        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("c_hash");
        hashOperations.delete("field1", "field2");
        hashOperations.put("field4", "value4");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    // 有序集合
    @RequestMapping(value = "/zset")
    @ResponseBody
    public Map<String, Object> zSet() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    /**
     * 测试Redis的流水线Pipeline
     */
    @RequestMapping(value = "/pipeline")
    @ResponseBody
    public Map<String, Object> pipeline() {
        long start = System.currentTimeMillis();
        // 切换数据库
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        connectionFactory.getStandaloneConfiguration().setDatabase(1);
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= 100000; i++) {
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    if (i == 100000)
                        System.out.println("命令进队列，还没有执行，查不到数据");
                }
                return null;
            }
        });

        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) + " 毫秒");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 测试SpringBoot + Redis缓存机制
     */
    @RequestMapping(value = "/getuser")
    @ResponseBody
    public User getUser(int id) {
        return userRedisService.getUser(id);
    }

    @RequestMapping(value = "/insertuser")
    @ResponseBody
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userRedisService.insertUser(user);
        return user;
    }

    @RequestMapping(value = "/findusers")
    @ResponseBody
    public List<User> findUsers(String userName, String note) {
        return userRedisService.findUsers(userName, note);
    }

    @RequestMapping(value = "/updateusername")
    @ResponseBody
    public Map<String, Object> updateUserName(int id, String userName) {
        User user = userRedisService.updateUserName(id, userName);
        boolean flag = user != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    @RequestMapping(value = "/deleteuser")
    @ResponseBody
    public Map<String, Object> deleteUser(int id) {
        int result = userRedisService.deleteUser(id);
        boolean flag = result == 1;
        String message = flag ? "删除成功" : "删除失败";
        return resultMap(flag, message);
    }

    private Map<String, Object> resultMap(boolean success, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}
