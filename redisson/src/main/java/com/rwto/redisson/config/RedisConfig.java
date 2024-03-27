package com.rwto.redisson.config;

import lombok.Data;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author renmw
 * @create 2024/3/26 9:54
 **/
@Data
@Configuration
public class RedisConfig {

    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public RBlockingDeque<String> getRBlockingDeque(){
        return redissonClient.getBlockingDeque("test_queue");
    }

    @Bean
    public RDelayedQueue<String> getRDelayedQueue(RBlockingDeque<String> blockingDeque){
        return redissonClient.getDelayedQueue(blockingDeque);
    }

    /**
     * RedisTemplate可以接收任意Object作为值写入Redis，只不过写入前会把Object序列化为字节形式，默认是采用JDK序列化，得到的一串很长的值
     * 缺点：可读性查、浪费存储空间
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //1.创建 redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //2.设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //3.设置序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //key 和 hashkey 采用 String 序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //value使用json序列化
        redisTemplate.setValueSerializer(RedisSerializer.json());
        //hashvalue 采用 json 序列化
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return  redisTemplate;
    };

}
