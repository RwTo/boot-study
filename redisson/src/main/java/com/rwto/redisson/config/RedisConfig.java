package com.rwto.redisson.config;

import lombok.Data;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renmw
 * @create 2024/3/26 9:54
 **/
@Data
@Configuration
public class RedisConfig {

    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public RBlockingDeque<String> getRBlockingDeque(){
        return redissonClient.getBlockingDeque("test_queue");
    }

    @Bean
    public RDelayedQueue<String> getRDelayedQueue(RBlockingDeque<String> blockingDeque){
        return redissonClient.getDelayedQueue(blockingDeque);
    }
}
