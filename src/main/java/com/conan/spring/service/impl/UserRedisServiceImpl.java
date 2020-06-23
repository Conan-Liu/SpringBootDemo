package com.conan.spring.service.impl;

import com.conan.spring.dao.UserRedisMapper;
import com.conan.spring.pojo.redis.User;
import com.conan.spring.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRedisServiceImpl implements UserRedisService {

    @Autowired
    private UserRedisMapper userRedisMapper;

    /**
     * value = "redisCache" 这个值是SpringBoot配置中spring.cache.cache-names对应的缓存名称
     * key 与配置文件spring.cache.redis.key-prefix的值拼接在一起，表示Redis里面的键的名称，这是使用EL表达式
     * 读操作，如排名，往往不是实时，会存在延时，可以设置Redis键的超时时间，当键超时后，不能够从缓存读数据，必须从数据库读取最新数据
     * 为了平衡数据库的压力和redis的准确性，超时时间需要考虑大小
     */
    @Override
    @Cacheable(value = "redisCache", condition = "#result != null", key = "'redis_user_' + #id")
    public User getUser(int id) {
        return userRedisMapper.getUser(id);
    }

    @Override
    @CachePut(value = "redisCache", key = "'redis_user_' + #result.id")
    public User insertUser(User user) {
        userRedisMapper.insertUser(user);
        return user;
    }

    // 更新数据后需要更新缓存，如果condition配置项使结果返回为null，不缓存
    // 写操作，一般认为缓存是不可信的，需要先从数据读取最新数据，然后再更新，避免将缓存的脏数据写入数据库，导致业务问题
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != null", key = "'redis_user_' + #id")
    public User updateUserName(int id, String userName) {
        // Spring的缓存机制也是基于AOP，此处调用getUser方法，同一个类里面调用，属于自调用，是原对象调用方法，而不是Spring代理对象调用，注解失效，所以还是执行sql去数据库中查询最新数据
        User user = getUser(id);
        if (user == null) {
            return null;
        }
        user.setUserName(userName);
        userRedisMapper.updateUser(user);
        return user;
    }

    // 批量查找用户，缓存命中率低，且大数据量的情况下消耗资源问题，不建议采用缓存机制
    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return userRedisMapper.findUsers(userName, note);
    }

    // 用户被删除，缓存也要移除
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_' + #id")
    public int deleteUser(int id) {
        return userRedisMapper.deleteUser(id);
    }
}
