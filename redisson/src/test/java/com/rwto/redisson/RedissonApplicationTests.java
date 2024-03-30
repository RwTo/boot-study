package com.rwto.redisson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedissonApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        //stringRedisTemplate.opsForValue().set("null",null);
        stringRedisTemplate.opsForHash().put("hashNull","null",null);
    }

}
