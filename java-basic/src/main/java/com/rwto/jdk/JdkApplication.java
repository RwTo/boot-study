package com.rwto.jdk;

import com.rwto.jdk.other.bean.CertificateTemplates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CertificateTemplates.class)
public class JdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdkApplication.class, args);
    }

}
