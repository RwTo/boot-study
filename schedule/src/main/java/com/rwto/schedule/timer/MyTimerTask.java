package com.rwto.schedule.timer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @author renmw
 * @create 2024/2/27 13:16
 **/
@Slf4j
public class MyTimerTask extends TimerTask {

    private String name;

    public MyTimerTask(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("MyTimerTask：{} this timestamp is {}", name , System.currentTimeMillis()/1000);
        Thread.sleep(3000);
    }
}
