package com.rwto.jdk.threadpool;

import java.util.concurrent.RecursiveTask;

/**
 * @author renmw
 * @since 2025/12/18 18:57
 **/
public class ForkTask extends RecursiveTask<Long> {
    private final int begin;
    private final int end;

    public ForkTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if(end - begin < 1000) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (begin + end) / 2;
            ForkTask left = new ForkTask(begin, mid);
            ForkTask right = new ForkTask(mid + 1, end);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
