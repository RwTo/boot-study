package com.rwto.rabbitmq.consumer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author renmw
 * @create 2024/1/29 15:07
 **/
@Slf4j
@Component
public class WorkQueueListener {

    /**
     * 如何解决消息堆积的问题
     * 1. 增加多个消费者
     * 2. 均衡消费者之间的性能，设置Prefetch=1
     * 3. 多线程
     * */

    /**默认情况下，MQ推送消息是轮询的，每个监听器一次轮询一批数据Prefetch，默认250个*/
    @RabbitListener(queues = MQContent.WORK_QUEUE)
    public void ListenWorkQueue1(String msg) throws InterruptedException {
        log.info("ListenWorkQueue1 消费消息：{}",msg);
        Thread.sleep(2000);
    }

    @RabbitListener(queues = MQContent.WORK_QUEUE)
    public void ListenWorkQueue2(String msg) throws InterruptedException {
        log.info("ListenWorkQueue2 消费消息：{}",msg);
        Thread.sleep(20);
    }
}
