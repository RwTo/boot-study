package com.rwto.zookeeper.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;



/**
 * @author renmw
 * @create 2024/3/29 19:07
 **/
@Data
@Slf4j
@Configuration
@EnableConfigurationProperties(ZKProperties.class)
public class ZKConfig {

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework(ZKProperties zkProperties) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProperties.getBaseSleepTimeMs(), zkProperties.getMaxRetries());

        // 工厂创建，fluent风格
        CuratorFramework client = CuratorFrameworkFactory.builder()
                // ip端口号"192.168.133.133:2181,192.168.133.133:2182,192.168.133.133:2183"
                .connectString(zkProperties.getConnectString())
                // 会话超时
                .sessionTimeoutMs(zkProperties.getSessionTimeoutMs())
                // 重试机制，这里是超时后1000毫秒重试一次
                .retryPolicy(new RetryOneTime(zkProperties.getBaseSleepTimeMs()))
                // 名称空间，在操作节点的时候，会以这个为父节点
                .namespace("rwto")
                .build();
        return client;
    }

}

