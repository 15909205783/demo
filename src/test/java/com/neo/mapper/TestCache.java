package com.neo.mapper;

import com.alibaba.fastjson.JSON;
import com.neo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCache {
    private Logger logger = LoggerFactory.getLogger(TestCache.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testCache() {
        long begin = System.currentTimeMillis();
        List<User> users = userMapper.getAll();
        long ing = System.currentTimeMillis();
        userMapper.getAll();
        long end = System.currentTimeMillis();
        logger.info("第一次请求时间：" + (ing - begin) + "ms");
        logger.info("第二次请求时间:" + (end - ing) + "ms");

        Assert.assertNotNull(users);
        logger.debug(JSON.toJSONString(users));
    }
}
