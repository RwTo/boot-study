package com.rwto.dubbo.service;

import com.rwto.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author renmw
 * @create 2024/3/30 16:37
 **/
@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
