package com.rwto.jdk;

import com.rwto.jdk.json.ExpressionEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * @author renmw
 * @since 2025/12/11 15:33
 **/
@SpringBootTest
public class ThreadPoolTest {

    @Test
    public void test01(){
        LinkedBlockingDeque<Object> blockingDeque = new LinkedBlockingDeque<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20,
                0l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        executor.execute(()->{
            System.out.println("111");
        });

        /*不再接受新的任务，等待任务执行完释放线程*/
        /*通过设置中断标记，中断take和poll的阻塞状态*/
        executor.shutdown();
        /*不在接受任务，当前任务中断*/
        executor.shutdownNow();
    }

}
