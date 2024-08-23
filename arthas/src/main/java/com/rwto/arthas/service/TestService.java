package com.rwto.arthas.service;

import com.alibaba.fastjson.JSONObject;
import com.rwto.arthas.domain.request.UserReq;
import com.rwto.arthas.domain.response.UserResp;
import org.springframework.stereotype.Service;

/**
 * @author renmw
 * @create 2024/8/22 11:20
 **/
@Service
public class TestService {

    public String business(UserReq userReq) throws InterruptedException {
        Thread.sleep(2000);
        return "1231";
    }

    public UserResp register(UserReq userReq, String str) throws InterruptedException {
        redis(userReq);
        mysql(userReq);
        UserResp userResp = JSONObject.parseObject(JSONObject.toJSONString(userReq), UserResp.class);
        userResp.setId(str);
        return userResp;

    }

    private void mysql(UserReq userReq) throws InterruptedException {
        Thread.sleep(5000);
    }

    private void redis(UserReq userReq) throws InterruptedException {
        Thread.sleep(200);
    }

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void deadLock1() {
        new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock2){

                }
            }
        }).start();
    }


    public void deadLock2() {
        new Thread(()->{
            synchronized (lock2){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock1){

                }
            }
        }).start();
    }
}
