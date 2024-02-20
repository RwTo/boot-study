package com.rwto.rabbitmq.config;

import com.rwto.rabbitmq.content.MQContent;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renmw
 * @create 2024/2/20 21:36
 **/
@Configuration
@ConditionalOnProperty(value = "spring.rabbitmq.listener.simple.retry",name = "enabled",havingValue = "true")
public class ErrorMQConfig {


    @Bean(MQContent.ERROR_QUEUE)
    public Queue getErrorQueue(){
        return new Queue(MQContent.ERROR_QUEUE);
    }

    /*error 交换机*/
    @Bean(MQContent.ERROR_EXCHANGE)
    public DirectExchange getErrorExchange(){
        return ExchangeBuilder.directExchange(MQContent.ERROR_EXCHANGE).build();
    }

    @Bean
    public Binding errorExchangeBindingQueue(@Qualifier(MQContent.ERROR_EXCHANGE) DirectExchange exchange, @Qualifier(MQContent.ERROR_QUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(MQContent.ROUTING_KEY_ERROR);
    }

    /**失败消息处理策略*/
    @Bean
    public MessageRecoverer republishMessageRecover(RabbitTemplate rabbitTemplate){
        /*发送到失败交换机，可用于发送邮件通知人工处理*/
        return new RepublishMessageRecoverer(rabbitTemplate,MQContent.ERROR_EXCHANGE,MQContent.ROUTING_KEY_ERROR);
    }
}
