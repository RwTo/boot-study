package com.rwto.redisson.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Data
@Slf4j
public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("123");
            return "123";
        });
 
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                log.info("任务成功完成，结果是: " + result);
            } else {
                log.info("任务失败，异常是: " + exception);
            }
        });
 
        executor.shutdown();
    }
}