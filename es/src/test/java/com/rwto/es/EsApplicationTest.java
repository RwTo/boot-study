package com.rwto.es;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author renmw
 * @create 2024/2/25 18:36
 **/
@SpringBootTest
public class EsApplicationTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void getTestUser() throws IOException {
        GetRequest request = new GetRequest("user","1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        if(response.isExists()){
            System.out.println(response.getId());
            System.out.println(response.getVersion());
            System.out.println(response.getSourceAsString());
        }

    }
}
