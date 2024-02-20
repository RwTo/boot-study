package com.rwto.rabbitmq.producer;

import com.rwto.rabbitmq.content.MQContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author renmw
 * @create 2024/1/29 15:38
 **/
@Slf4j
@RestController
public class AdvancedProducer {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送可以确认的消息
     * 将消息广播到不同的队列
     * @param msg
     * @return
     */
    @RequestMapping(path = "/confirmMessage")
    public String confirmMessage(String msg) {
        if(null == msg){
            return "error null";
        }
        CorrelationData correlationData = new CorrelationData();//内部含有uuid，充当消息的唯一id
        correlationData.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息回调失败");
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.debug("收到消息回调");
                if (result.isAck()){
                    /** ack的情况：
                         1. 成功到达交换机，但路由失败，没有到达队列，此时会触发return机制
                         2. 临时消息到达交换机 并且路由给队列
                         3. 持久消息到达交换机 并且路由给队列并且持久化到磁盘
                     * */
                    log.info("消息发送成功，收到ack，消息内容：{}",msg);
                }else{
                    log.error("消息发送失败，收到nack，消息内容：{}, 原因：{}",msg,result.getReason());
                    //TODO: 有限次 的重发消息
                }
            }
        });
        rabbitTemplate.convertAndSend("MQContent.DIRECT_EXCHANGE",MQContent.ROUTING_KEY_DIRECT,msg,correlationData);


        return "confirmMessage 发送消息 ：" + msg;
    }


    /**
     * 发送非持久化的消息
     * @param msg
     * @return
     */
    @RequestMapping(path = "/nonPersistentMessage")
    public String nonPersistentMessage(String msg) {
        if(null == msg){
            return "error null";
        }
        Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
        rabbitTemplate.convertAndSend(MQContent.DIRECT_EXCHANGE, MQContent.ROUTING_KEY_DIRECT, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //消息后置处理器
                //message.getMessageProperties().setExpiration("10000");
                //message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                return message;
            }
        });


        return "nonPersistentMessage 发送消息 ：" + msg;
    }

}
