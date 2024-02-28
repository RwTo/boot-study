package com.rwto.schedule.pool;

import com.rwto.schedule.timer.MyTimerTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/2/27 15:46
 **/
@SpringBootTest
public class SchedulePoolTest {

    @Test
    public void test1(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 2; i++) {
            executorService.schedule(new MyTimerTask("pool"+i), 0,  TimeUnit.SECONDS);
        }
        while (true);
    }

    @Test
    public void test2(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 2; i++) {
            executorService.scheduleAtFixedRate(new MyTimerTask("pool"+i), 0,  2, TimeUnit.SECONDS);
        }
        while (true);
    }
}
