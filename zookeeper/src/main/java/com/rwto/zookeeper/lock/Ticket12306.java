package com.rwto.zookeeper.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/3/30 0:56
 **/
public class Ticket12306 implements Runnable{

    private volatile int tickets = 10;

    private InterProcessMutex lock;

    public Ticket12306(InterProcessMutex lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            /*获取锁*/
            try {
                boolean acquire = lock.acquire(3, TimeUnit.SECONDS);
                if(!acquire){
                    continue;
                }
                if(tickets > 0){
                    System.out.println(Thread.currentThread()+":"+tickets);
                    Thread.sleep(100);
                    tickets--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                /*释放锁*/
                try {
                    lock.release();
                } catch (Exception e) {
                }
            }
        }
    }
}
