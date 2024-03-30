package com.rwto.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ZookeeperApplicationTests {

    @Resource
    private CuratorFramework curatorFramework;

    /**
     * 创建节点  持久，临时 -e （有序 -s）
     */
    @Test
    public void createZNode1() throws Exception {
        //默认数据为 当前客户端的ip  /rwto/path1 -> ip
        String path1 = curatorFramework.create().forPath("/path1");
        System.out.println(path1);

        String path2 = curatorFramework.create().forPath("/path2","hello zookeeper".getBytes());
        System.out.println(path2);

    }


    @Test
    public void createZNode2() throws Exception {
        String path = curatorFramework.create().forPath("/path2","hello zookeeper".getBytes());
        System.out.println(path);
    }

    @Test
    public void createZNode3() throws Exception {
        /*临时节点，当前会话结束后，临时节点失效*/
        String path = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/path3", "hello zookeeper".getBytes());
        System.out.println(path);
    }

    @Test
    public void createZNode4() throws Exception {
        /*创建多级节点*/
        String path = curatorFramework.create().creatingParentsIfNeeded().forPath("/path3/app", "hello zookeeper".getBytes());
        System.out.println(path);
    }


    /**
     * 查询数据 get
     * 查询子节点 ls
     * 查询节点状态信息 ls -s
     * */

    @Test
    public void get1() throws Exception {
        /*查询数据 get*/
        byte[] bytes = curatorFramework.getData().forPath("/path2");
        System.out.println(new String(bytes));
    }

    @Test
    public void get2() throws Exception {
        /*查询数据 子节点*/
        /** 命名空间是/rwto */
        List<String> paths = curatorFramework.getChildren().forPath("/");
        System.out.println(paths);
    }


    @Test
    public void get3() throws Exception {
        /*查询节点状态信息 ls -s*/
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/path2");
        System.out.println(new String(bytes));
        System.out.println(stat);
    }

    /**
     * 修改节点
     * 修改数据 set
     * 根据版本修改
     * */

    @Test
    public void set1() throws Exception {
        /*set /path2 sss*/
        Stat stat = curatorFramework.setData().forPath("path2", "update zookeeper".getBytes());
        System.out.println(stat);
    }


    @Test
    public void setForVersion() throws Exception {
        /*根据版本修改*/
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/path2");
        int version = stat.getVersion();
        curatorFramework.setData().withVersion(version).forPath("path2", "update zookeeper".getBytes());

        /*版本不对，会抛异常*/
    }

    /**
     * 删除节点
     * delete deleteAll
     * 1. 删除单个节点
     * 2. 删除带有子节点的节点
     * 3. 必须删除成功（重试删除）
     * 4. 删除回调
     */
    @Test
    public void delete1() throws Exception {
        /*删除单个节点*/
        curatorFramework.delete().forPath("/path5");

        /*有子节点，会抛异常*/
    }

    @Test
    public void delete2() throws Exception {
        /*删除带有子节点的节点 deleteall*/
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/path4");
    }

    @Test
    public void delete3() throws Exception {
        /*必须删除成功 如果网络抖动，会重试删除，直到删除成功*/
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/path4");

    }

    @Test
    public void delete4() throws Exception {
        /*必须删除成功 如果网络抖动，会重试删除，直到删除成功*/
        curatorFramework.delete().guaranteed().inBackground((client,event)->{
            System.out.println("删除成功！");
            System.out.println(event);
        }).forPath("/path4");

    }
}
