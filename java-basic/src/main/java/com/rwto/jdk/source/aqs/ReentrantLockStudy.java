package com.rwto.jdk.source.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renmw
 * @create 2024/4/28 17:01
 **/
public class ReentrantLockStudy {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();


        try {
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {

        }

        try {
            reentrantLock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reentrantLock.unlock();
    }
}
