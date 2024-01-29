package com.rwto.rabbitmq.producer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

/**
 * @author renmw
 * @create 2024/1/29 13:59
 **/
@Slf4j
@RestController
public class SpringRabbitProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(path = "/productSimpleMessage")
    public String productSimpleMessage(String msg){
        if(null == msg){
            return "error null";
        }
        /*创建一个队列，默认是绑定的直连交换机，routingKey 就是队列名*/
        rabbitTemplate.convertAndSend(MQContent.SIMPLE_QUEUE,msg);
        return "发送消息：" + msg;
    }


    @RequestMapping(path = "/productWorkQueueMessage")
    public String productSimpleMessage(Integer num){
        if(null == num){
            return "error null";
        }
        while(num-- > 0){
            rabbitTemplate.convertAndSend(MQContent.WORK_QUEUE,num);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        return "发送消息 WORK_QUEUE ：" + num;
    }



}
