package com.rwto.redisson.service;

import com.rwto.redisson.exception.CommonException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/3/27 15:19
 **/
@Service
public class StockService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private final static String STOCK = "stock:";
    private final static String LOCK_KEY = "lock_key:";

    public String reduceStocks(String stock){
        String stockKey = STOCK+stock;
        Integer num = (Integer) redisTemplate.opsForValue().get(stockKey);
        if(null == num || num <= 0){
            throw new CommonException("100001","库存不足");
        }

        Long decrement = redisTemplate.opsForValue().decrement(stockKey);
        return "剩余："+decrement;

    }


    public String incrStocks(String stock, Integer addNum){
        String stockKey = STOCK+stock;
        Integer num = (Integer) redisTemplate.opsForValue().get(stockKey);
        if(null == num){
            num = 0;
        }
        num += addNum;
        if (num < 0){
            throw new CommonException("100001","库存错误");
        }
        redisTemplate.opsForValue().set(stockKey,num);
        return "success";
    }

    /**
     * 通过setnx 做分布式锁
     * setnx：如果key不存在，则正常使用，如果key已存在则不进行任何操作,返回false
     * @param stock
     */
    public String reduceStocksBySNX(String stock){
        String lock = LOCK_KEY+stock;
        /*Boolean absent = redisTemplate.opsForValue().setIfAbsent(lock, stock);

        //必须设置超时时间，防止机器宕机造成的锁死
        redisTemplate.expire(lock,30, TimeUnit.SECONDS);*/
        /**使用redis自带的原子命令，setnx的同时设置超时时间 */
        /** 设置锁时，保证原子性，防止设置锁之后，服务宕机，导致锁死*/
        Boolean absent = redisTemplate.opsForValue().setIfAbsent(lock, stock, 30, TimeUnit.SECONDS);

        /*
          如果redis key 已过期，任务还没执行完，锁已释放
          那么别的线程可能重新生成锁
          当前线程可能删除删除其他线程的锁

          问题1: 删除别人的lock    ->  value 设置自己的id，只能删除自己创建的lock (要保证原子性)
          问题2：任务没执行完锁失效了  ->  锁续期  -> 看门狗机制
        * */


        if(!absent){
            throw new CommonException("10001","请稍后再试");
        }

        try {
            return reduceStocks(stock);
        }catch (Exception e){
            throw new CommonException("10001",e.getMessage());
        }finally {
            /*一定放在finally里，防止锁死*/
            /*但是考虑有机器宕机的可能，必须给lock加一个过期时间，否则还是可能锁死*/
            /**释放锁时，保证原子性，防止误删别人锁，准备删锁的时候，锁过期了，另一个线程又正好加上了锁*/
            redisTemplate.delete(lock);
        }
    }



    public String reduceStocksByRedisson(String stock) throws InterruptedException {
        String lock = LOCK_KEY+stock;
        /*内部使用hash存value，保证可重入*/
        RLock clientLock = redissonClient.getLock(lock);
        //clientLock.lock();
        clientLock.lock(10,TimeUnit.SECONDS);
        /*并行串行化*/
        //boolean b = clientLock.tryLock(10, 10, TimeUnit.SECONDS);
        try {
            return reduceStocks(stock);
        }catch (Exception e){
            throw new CommonException("10001",e.getMessage());
        }finally {
            clientLock.unlock();
        }
    }

    /*Redis集群 保证CP ，Zookeeper集群 保证 AP*/

    /*主从切换时，分布式锁丢失的问题，（主节点还没同步给从节点，主节点挂了，导致锁丢失）*/
    /**
     RedLock 争论  生产有很多坑
     连接所有redis节点（最少三个），半数节点加锁成功才算成功，类似zookeeper 分布式锁
     问题1：节点不需要高可用，本身多个机器就是高可用，但机器越多，性能越低
     问题2：建议节点个数必须是奇数且最少3，因为挂了一台，还可以正常使用，满足半数加锁成功，
     问题3：最多允许挂一台机器
     问题4：aof持久化 配置 everySec，如果没有持久化的时候，重启机器，可能出现错误
    * */


    /*使用分布式锁，导致并行串行化，如何提高并发性能*/
    /**
     性能优化
     1. 锁粒度降低
     2. 分段锁，每个key管理一部分库存  ConcurrentHashMap就是用的分段锁
     3.
     */


}
