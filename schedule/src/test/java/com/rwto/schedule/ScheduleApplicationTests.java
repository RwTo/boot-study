package com.rwto.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedList;

@SpringBootTest
class ScheduleApplicationTests {

    @Test
    void contextLoads() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        boolean b = hashMap.containsKey(null);

        hashMap.put(null,null);

        boolean b1 = hashMap.containsKey(null);
    }

}
