package com.rwto.schedule.timer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Timer;

/**
 * @author renmw
 * @create 2024/2/27 13:17
 **/
@SpringBootTest
public class TimerTest {

    @Test
    public void test01(){
        Timer timer = new Timer();//任务启动
        for (int i = 0; i < 2; i++) {
            MyTimerTask myTimerTask = new MyTimerTask(String.valueOf(i));
            timer.schedule(myTimerTask,new Date(),2000);//任务添加
            //基于当前时间，少执行任务，串行化执行
        }
        while(true);
    }

    @Test
    public void test02(){
        Timer timer = new Timer();//任务启动
        for (int i = 0; i < 2; i++) {
            MyTimerTask myTimerTask = new MyTimerTask(String.valueOf(i));
            timer.scheduleAtFixedRate(myTimerTask,new Date(),2000);//任务添加
            //基于执行时间，执行时间会乱，前一个任务执行完后会马上触发
        }
        while(true);
    }
}
