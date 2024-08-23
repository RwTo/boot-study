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
    /*salary*/
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
    /*salary 16612，17123*/
    public void test5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double x ;//总 整数
        double y ; //实
        for(x = 17000 ; x < 30000; x+=500){
            double y1 = x * 0.12;
            double y2 = (x-y1-600-5000) * 0.1;
            y = x-y1-600-y2;
            System.out.println("x= "+x + " y1*2= "+y1*2 + " y2 = "+ y2 +" y = "+ y);

            System.out.println("===================");
        }
    }

    @Test
    /*salary 16612，17123*/
    public void test7() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double x ;//总 整数
        double y ; //实
        for(x = 17000 ; x < 40000; x+=500){
            double y1 = x * 0.12;
            double y2 = x * 0.1;
            double y3 = (x-y1-y2-5000) * 0.1;
            y = x-y1-y2-y3;
            System.out.println("x= "+x + " y1*2= "+y1*2 + " y2 = "+ y2 + " y3 = "+ y3 +" y = "+ y);

            System.out.println("===================");
        }
    }

    @Test
    public void test6(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>());



        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.remove();
    }
}
