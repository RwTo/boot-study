package com.rwto.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author renmw
 * @create 2024/2/20 15:53
 **/
@Slf4j
@Configuration
public class CommonConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        //rabbitTemplate.setConfirmCallback();
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.error("消息投递到队列失败：消息内容：{} 回应码：{} 回应消息：{} 交换机：{} 路由键：{}"
                    ,returnedMessage.getMessage()
                    ,returnedMessage.getReplyCode()
                    ,returnedMessage.getReplyText()
                    ,returnedMessage.getExchange()
                    ,returnedMessage.getRoutingKey());
        });
    }
}
