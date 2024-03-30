package com.rwto.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author renmw
 * @create 2024/3/29 19:41
 **/
@Data
@ConfigurationProperties(prefix = "zookeeper")
public class ZKProperties {

    private String connectString;


    private int baseSleepTimeMs;


    private int maxRetries ;


    private int connectionTimeoutMs ;


    private int sessionTimeoutMs;
}
