package com.rwto.redisson.controller;

import com.rwto.redisson.service.RedissonDelayQueueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/1/18 11:14
 **/
@Slf4j
@RestController
@RequestMapping("/delayQueue2")
public class DelayQueueController {
    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private RedissonDelayQueueService redissonDelayQueueService;

    @RequestMapping("/redisson")
    public void redissonTest(Integer time) throws InterruptedException {
        String lockKey = "lockKey:123";
        RLock lock = redissonClient.getLock(lockKey);
        if(!lock.tryLock(10, TimeUnit.SECONDS)){
            return;
        }
        try {
            Thread.sleep(time*1000);
            System.out.println(time);
        } catch (InterruptedException e) {

        }finally {
            lock.unlock();
        }

    }



    @RequestMapping("/delayQueueOffer")
    public void delayQueue(String str) {
        System.out.println(System.currentTimeMillis());
        int sum = 0;
        for(int i = 0; i< 10000; i++){
            redissonDelayQueueService.offer(""+i,5L);
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis());
    }
}
