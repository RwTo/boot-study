package com.rwto.schedule.pool;

import com.rwto.schedule.timer.MyTimerTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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


    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.getClass().getMethod("add",Object.class).invoke(a,"asf");
        System.out.println(a);
    }


    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int x ;//总 整数
        double y ; //实
        for(x = 40000 ; x < 60000; x+=1000){
            y = x * 0.97;
            System.out.println("x= "+x + " y= "+y + " z = "+ (int)(x/2.3));
            y = (x+210)*0.9;
            System.out.println("x= "+x + " y= "+y + " z = "+ (int)(x/2.3));
            System.out.println("===================");
        }
    }

    @Test
    public void test5(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>());



        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.remove();
    }
}
