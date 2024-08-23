package com.rwto.arthas.controller;

import com.rwto.arthas.domain.request.UserReq;
import com.rwto.arthas.domain.response.UserResp;
import com.rwto.arthas.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author renmw
 * @create 2024/8/22 11:20
 **/
@RestController
@RequestMapping("arthas")
public class TestController {

    private static volatile boolean cpuSwitch = true;

    @Autowired
    private TestService testService;

    /**
     * 使cpu使用率达到100
     * @return
     */
    @RequestMapping("cpu100")
    public String cpu100(){
        cpuSwitch = true;
        while(cpuSwitch){

        }
        return "OK";
    }

    /**
     * 关闭cpu100开关
     * @return
     */
    @RequestMapping("cpuSwitchOff")
    public String cpuSwitchOff(){
        cpuSwitch = false;
        return "OK";
    }

    /**
     * 模拟注册用户（耗时请求）
     * 业务处理 2s
     * redis 0.2s
     * mysql 5s
     * @param userReq
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("registerUser")
    public UserResp registerUser(UserReq userReq) throws InterruptedException {
        String str = testService.business(userReq);
        return testService.register(userReq,str);
    }

    /**
     * 5s内 连续调用 deadLock1，deadLock2 会造成死锁
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("deadLock1")
    public String deadLock1() throws InterruptedException {
        testService.deadLock1();
        return "OK";
    }

    /**
     * 5s内 连续调用 deadLock1，deadLock2 会造成死锁
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("deadLock2")
    public String deadLock2() throws InterruptedException {
        testService.deadLock2();
        return "OK";
    }
}
