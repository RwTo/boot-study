package com.rwto.rabbitmq.consumer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author renmw
 * @create 2024/1/29 13:53
 **/
@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = MQContent.SIMPLE_QUEUE)
    public void ListenSimpleMessage(String msg){
        log.info("消费消息：{}",msg);
    }
}
