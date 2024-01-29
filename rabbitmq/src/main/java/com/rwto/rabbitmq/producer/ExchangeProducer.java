package com.rwto.rabbitmq.producer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author renmw
 * @create 2024/1/29 15:38
 **/
@Slf4j
@RestController
public class ExchangeProducer {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 广播模式
     * 将消息广播到不同的队列
     * @param msg
     * @return
     */
    @RequestMapping(path = "/fanoutMessage")
    public String productFanoutMessage(String msg){
        if(null == msg){
            return "error null";
        }
        /** 广播模式 没有routingKey，可以传任何值 包括 null*/
        rabbitTemplate.convertAndSend(MQContent.FANOUT_EXCHANGE,"",msg);

        return "广播发送 发送消息 ：" + msg;
    }


    @RequestMapping(path = "/directMessage")
    public String productDirectMessage(String msg){
        if(null == msg){
            return "error null";
        }
        /** 直连模式 根据routingKey 判断发送到 什么队列*/
        rabbitTemplate.convertAndSend(MQContent.DIRECT_EXCHANGE,MQContent.ROUTING_KEY_DIRECT,msg);

        return "直连发送 发送消息 ：" + msg;
    }


    @RequestMapping(path = "/topicMessage")
    public String productTopicMessage(String msg){
        if(null == msg){
            return "error null";
        }
        /** 主题模式 根据routingKey 模糊匹配，判断发送到 什么队列*/
        /** 发送时要指定准确的routingKey，绑定时绑定模糊的routingKey（bindingKey）*/
        rabbitTemplate.convertAndSend(MQContent.TOPIC_EXCHANGE,"topic.routing.key",msg);

        return "主题发送 发送消息 ：" + msg;
    }
}
