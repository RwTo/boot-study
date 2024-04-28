package com.rwto.jdk.java8.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @author renmw
 * @create 2024/4/22 14:28
 **/
@Data
@Component
@PropertySource(value = "classpath:certificate.yml",factory = YamlPropertySourceFactory.class)
@ConfigurationProperties("certificate")
public class CertificateTemplates {
    private Map<String, CertificateTemplate> templates = new HashMap<>();

}
