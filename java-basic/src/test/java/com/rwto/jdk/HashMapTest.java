package com.rwto.jdk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author renmw
 * @create 2024/8/28 15:21
 **/
@SpringBootTest
public class HashMapTest {

    @Test
    public void test01(){
        HashMap<String, Object> map = new HashMap<>(1);

        Set<Map.Entry<String, Object>> entries = map.entrySet();

        map.put("key1",1);
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

        /*方法都被synchronized 修饰，线程安全，但性能低*/
        Hashtable<Object, Object> hashtable = new Hashtable<>();

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("key1","1");

    }
}
