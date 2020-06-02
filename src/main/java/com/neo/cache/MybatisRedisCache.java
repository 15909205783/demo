package com.neo.cache;


import com.neo.autil.SpringContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {
    private Logger log = LoggerFactory.getLogger(MybatisRedisCache.class);
    private String namespace; //存储namespace
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private String id;

    private RedisTemplate redisTemplate = (RedisTemplate) SpringContextHolder.getBean("redisTemplate");
    private long timeout = 300;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (value != null) {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value, timeout, TimeUnit.SECONDS);
        }
    }

    @Override
    public Object getObject(Object key) {
        log.debug("从缓存中读取结果.key:" + key);
        ValueOperations operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        log.debug("从缓存中清除.key:" + key);
        ValueOperations operations = redisTemplate.opsForValue();
        Object value = operations.get(key);
        redisTemplate.delete(key);
        return value;
    }

    @Override
    public void clear() {
        log.debug("从缓存区删除所有数据");
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushAll();
                return null;
            }
        });
    }

    @Override
    public int getSize() {
        int size = (int) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
        return size;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {

        return readWriteLock;
    }

    public void setTimeout() {
        this.timeout = timeout;
    }
}
