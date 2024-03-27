package com.rwto.redisson.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/3/26 11:52
 **/
@Slf4j
@Service
public class RedissonDelayQueueService {

    @Resource
    public RDelayedQueue<String> delayedQueue;

    @Resource
    private RBlockingDeque<String> rBlockingDeque;

    @PostConstruct
    public void init(){
        Thread thread = new Thread(() -> {
            int count = 0;
            int sum = 0;
            while (true) {
                try {
                    String str = rBlockingDeque.take();
                    log.info("str=" + str);
                    sum += Integer.parseInt(str);
                    count++;
                    log.info("count=" + count);
                    log.info("sum=" + sum);
                } catch (Exception e) {
                    log.error("error", e);
                }
            }
        });
        /*守护线程，如果所有用户线程都停止了，即使还有守护线程在运行，jvm也会退出，GC线程就是守护线程*/
        /*守护线程一般用作一些辅助工作*/
        thread.setDaemon(true);
        thread.start();
    }

    public void offer(String obj,Long sec){
        delayedQueue.offer(obj, sec, TimeUnit.SECONDS);
    }
}
