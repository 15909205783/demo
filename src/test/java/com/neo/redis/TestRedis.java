package com.neo.redis;

import com.neo.enums.UserSexEnum;
import com.neo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.opsForValue().set("bbb", "222");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    @Test
    public void testObj() throws Exception{
        User user = new User("aa","aa123456", UserSexEnum.WOMAN);
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("com.neox",user);
        operations.set("com.neo.f",user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exits = redisTemplate.hasKey("com.neo.f");
        if (exits){
            System.out.println("esits is true");
        }else {
            System.out.println("exists is false");
        }

    }
}
