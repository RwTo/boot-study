package com.rwto.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author renmw
 * @create 2024/3/29 23:50
 **/
/*zookeeper 允许用户在指定节点上注册一些Watcher，
并且在一些特定事件触发的时候，Zookeeper服务端会将事件通知到感兴趣的客户端上去
这一机制是ZooKeeper实现分布式协调服务的重要特性

实现了发布订阅功能，能多个订阅者同时监听某个对象，当一个对象自身状态变化，会通知所有订阅者

三种watcher
NodeCache 监听节点
PathChildrenCache  子节点
TreeCache  节点及子节点
*/
@SpringBootTest
public class ZKWatchTests {

    @Resource
    private CuratorFramework client;

    @Test
    public void watchNodeCache() throws Exception {
        /*创建NodeCache对象*/
        NodeCache nodeCache = new NodeCache(client, "/path1");

        nodeCache.getListenable().addListener(()->{
            System.out.println("节点变化");
            /*获取修改后的数据对象*/
            byte[] data = nodeCache.getCurrentData().getData();
            System.out.println(new String(data));
        });

        nodeCache.start();

        while (true);
    }

    @Test
    public void watchPathChildrenCache() throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client,"/path1",true);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("子节点变化");
                System.out.println(pathChildrenCacheEvent);

                if(PathChildrenCacheEvent.Type.CHILD_UPDATED.equals(pathChildrenCacheEvent.getType())){
                    byte[] data = pathChildrenCacheEvent.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });

        pathChildrenCache.start();

        while (true);

    }


    @Test
    public void watchTreeCache() throws Exception{
        TreeCache treeCache = new TreeCache(client, "/path1");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                System.out.println("Tree节点变化");
                System.out.println(event);
            }
        });

        treeCache.start();

        while (true);
    }
}
