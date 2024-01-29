package com.rwto.rabbitmq.config;

import com.rwto.rabbitmq.content.MQContent;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 用户创建队列，交换机，及绑定关系
 * 注意：
 * 使用过程中如果切换绑定关系，记得解绑之前的绑定关系
 * @author renmw
 * @create 2024/1/29 15:14
 **/
@Configuration
public class MQConfig {

    @Bean(MQContent.SIMPLE_QUEUE)
    public Queue getSimpleQueue(){
        return new Queue(MQContent.SIMPLE_QUEUE);
    }

    @Bean(MQContent.WORK_QUEUE)
    public Queue getWorkQueue(){
        return new Queue(MQContent.WORK_QUEUE);
    }

    @Bean(MQContent.FANOUT_QUEUE1)
    public Queue getFanoutQueue1(){
        return new Queue(MQContent.FANOUT_QUEUE1);
    }

    @Bean(MQContent.FANOUT_QUEUE2)
    public Queue getFanoutQueue2(){
        return new Queue(MQContent.FANOUT_QUEUE2);
    }


    @Bean(MQContent.DIRECT_QUEUE)
    public Queue getDirectQueue(){
        return new Queue(MQContent.DIRECT_QUEUE);
    }


    @Bean(MQContent.TOPIC_QUEUE)
    public Queue getTopicQueue(){
        return new Queue(MQContent.TOPIC_QUEUE);
    }


    /*Fanout 广播交换机*/
    @Bean(MQContent.FANOUT_EXCHANGE)
    public FanoutExchange getFanoutExchange(){
        return ExchangeBuilder.fanoutExchange(MQContent.FANOUT_EXCHANGE).build();
    }

    @Bean
    public Binding fanoutExchangeBindingQueue1(@Qualifier(MQContent.FANOUT_EXCHANGE) FanoutExchange exchange, @Qualifier(MQContent.FANOUT_QUEUE1) Queue queue){
        /** 广播模式，没有routingKey，只要与广播交换机绑定的队列，都会收到消息*/
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding fanoutExchangeBindingQueue2(@Qualifier(MQContent.FANOUT_EXCHANGE) FanoutExchange exchange,@Qualifier(MQContent.FANOUT_QUEUE2) Queue queue){
        /** 广播模式，没有routingKey，只要与广播交换机绑定的队列，都会收到消息*/
        return BindingBuilder.bind(queue).to(exchange);
    }

    /*direct 直连交换机*/
    @Bean(MQContent.DIRECT_EXCHANGE)
    public DirectExchange getDirectExchange(){
        return ExchangeBuilder.directExchange(MQContent.DIRECT_EXCHANGE).build();
    }

    @Bean
    public Binding directExchangeBindingQueue(@Qualifier(MQContent.DIRECT_EXCHANGE) DirectExchange exchange, @Qualifier(MQContent.DIRECT_QUEUE) Queue queue){
        /** 直连模式，根据routingKey绑定的队列，将消息发送到队列上，也可以实现广播模式(多个队列绑定同一个交换机)*/
        return BindingBuilder.bind(queue).to(exchange).with(MQContent.ROUTING_KEY_DIRECT);
    }

    /*topic 主题交换机*/
    @Bean(MQContent.TOPIC_EXCHANGE)
    public TopicExchange getTopicExchange(){
        return ExchangeBuilder.topicExchange(MQContent.TOPIC_EXCHANGE).build();
    }

    @Bean
    public Binding topicExchangeBindingQueue(@Qualifier(MQContent.TOPIC_EXCHANGE) TopicExchange exchange, @Qualifier(MQContent.TOPIC_QUEUE) Queue queue){
        /** 主题模式，根据动态routingKey绑定的队列，将消息发送到动态匹配的队列上，也可以实现广播模式(多个队列绑定同一个交换机)*/
        return BindingBuilder.bind(queue).to(exchange).with(MQContent.ROUTING_KEY_TOPIC);
    }
}
