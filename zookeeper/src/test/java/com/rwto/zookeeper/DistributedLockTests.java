package com.rwto.zookeeper;

import com.rwto.zookeeper.lock.Ticket12306;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author renmw
 * @create 2024/3/30 0:55
 **/
@SpringBootTest
public class DistributedLockTests {

    @Resource
    private CuratorFramework client;

    @Test
    public void lock() {
        InterProcessMutex mutex = new InterProcessMutex(client, "/lock");
        Ticket12306 ticket12306 = new Ticket12306(mutex);

        Thread t1 = new Thread(ticket12306, "携程");
        Thread t2 = new Thread(ticket12306, "飞猪");

        t1.start();
        t2.start();

        while (true);
    }
}
