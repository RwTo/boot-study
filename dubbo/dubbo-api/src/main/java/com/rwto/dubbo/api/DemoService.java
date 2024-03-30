package com.rwto.dubbo.api;

import com.rwto.dubbo.pojo.User;

/**
 * @author renmw
 * @create 2024/3/30 16:32
 **/
public interface DemoService {

    String sayHello(String name);

    User getUser(String id);
}
