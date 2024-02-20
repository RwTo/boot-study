package com.rwto.rabbitmq.content;

/**
 * @author renmw
 * @create 2024/1/29 15:10
 **/
public class MQContent {

    public static final String LAZY_QUEUE = "lazy.queue";
    public static final String SIMPLE_QUEUE = "simple.queue";
    public static final String WORK_QUEUE = "work.queue";
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String DIRECT_QUEUE = "direct.queue";
    public static final String TOPIC_QUEUE = "topic.queue";


    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String DIRECT_EXCHANGE = "direct.exchange";
    public static final String TOPIC_EXCHANGE = "topic.exchange";


    public static final String ROUTING_KEY_DIRECT = "direct.routing.key";
    /**
     * TOPIC主题交换机的通配符
     * #: 0-n 个单词
     * *: 1个单词
     * */
    public static final String ROUTING_KEY_TOPIC = "topic.routing.key.#";
}
