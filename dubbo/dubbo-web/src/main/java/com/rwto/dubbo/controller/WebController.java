package com.rwto.dubbo.controller;

import com.rwto.dubbo.api.DemoService;
import com.rwto.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author renmw
 * @create 2024/3/30 22:01
 **/
@RestController
public class WebController {

    /*timeout 和 retries 会覆盖 @DubboService 的配置*/
    @DubboReference(timeout = 4000,version = "v2.0",retries = 0,loadbalance = "random")
    private DemoService demoService;

    @RequestMapping("getUser")
    public User gerUserById(String id){
        AtomicInteger times = new AtomicInteger(20);
        new Thread(()->{
            while (times.getAndDecrement() > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(times.get());
            }
        }).start();
        return demoService.getUser(id);
    }

}
