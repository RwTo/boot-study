package com.rwto.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
@Slf4j
class RedissonApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        //stringRedisTemplate.opsForValue().set("null",null);
        stringRedisTemplate.opsForHash().put("hashNull","null",null);
    }


    @Test
    void test1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("123");
            return "123";
        });

        future.whenComplete((result, exception) -> {
            if (exception == null) {
                log.info("任务成功完成，结果是: " + result);
            } else {
                log.info("任务失败，异常是: " + exception);
            }
        });
        log.info("sss");
    }

}
