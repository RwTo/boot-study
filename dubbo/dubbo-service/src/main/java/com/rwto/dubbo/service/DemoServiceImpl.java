package com.rwto.dubbo.service;

import com.rwto.dubbo.api.DemoService;
import com.rwto.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author renmw
 * @create 2024/3/30 16:37
 **/
@DubboService(timeout = 1000,retries = 3,version = "v1.0",weight = 100)
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public User getUser(String id) {
        System.out.println(id);
        User user = new User();
        user.setAge(18);
        user.setId(id);
        user.setName("张三");
        user.setSex("男");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
