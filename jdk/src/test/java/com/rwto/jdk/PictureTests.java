package com.rwto.jdk;

import com.rwto.jdk.java8.bean.CertificateTemplate;
import com.rwto.jdk.java8.bean.CertificateTemplates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author renmw
 * @create 2024/4/22 15:07
 **/
@SpringBootTest
public class PictureTests {
    @Autowired
    private CertificateTemplates certificateTemplates;

    @Test
    public void test01(){
        System.out.println(certificateTemplates);
    }
}
