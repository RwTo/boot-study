package com.rwto.jdk;

import com.rwto.jdk.threadpool.ForkTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author renmw
 * @since 2025/12/18 18:56
 **/
@SpringBootTest
public class ForkJoinPoolTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        ForkJoinTask<Long> future = forkJoinPool.submit(new ForkTask(1, 10000));

        System.out.println("sum = " + future.get());
    }

}
