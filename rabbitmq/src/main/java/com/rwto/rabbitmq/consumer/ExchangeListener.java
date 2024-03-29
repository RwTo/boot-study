package com.rwto.rabbitmq.consumer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author renmw
 * @create 2024/1/29 15:56
 **/
@Slf4j
@Component
public class ExchangeListener {

    /** 广播模式，需要监听不同的队列 */
    @RabbitListener(queues = MQContent.FANOUT_QUEUE1)
    public void ListenFanoutMessage1(String msg){
        log.info("FANOUT_QUEUE1 消费消息：{}",msg);
    }


    @RabbitListener(queues = MQContent.FANOUT_QUEUE2)
    public void ListenFanoutMessage2(String msg){
        log.info("FANOUT_QUEUE2 消费消息：{}",msg);
    }


    @RabbitListener(queues = MQContent.DIRECT_QUEUE)
    public void ListenDirectMessage(String msg){
        log.info("DIRECT_QUEUE 消费消息：{}",msg);
    }


    @RabbitListener(queues = MQContent.TOPIC_QUEUE)
    public void ListenTopicMessage(String msg){
        log.info("TOPIC_QUEUE 消费消息：{}",msg);
    }


    /**注解方式创建，绑定并创建*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2",durable = "true"),
            exchange = @Exchange(name = "topic",type = ExchangeTypes.TOPIC),
            key = {"topic.key.#"}))
    public void ListenTopicMessage2(String msg){
        log.info("TOPIC_QUEUE 消费消息：{}",msg);
    }
}
