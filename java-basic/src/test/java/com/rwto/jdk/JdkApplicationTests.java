package com.rwto.jdk;

import com.rwto.jdk.other.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class JdkApplicationTests {

    @Test
    void contextLoads() {
        HashMap<Object, Object> map = new HashMap<>();

        Dog dog = new Dog();
        map.put(dog, "1");
        System.out.println(dog.hashCode());
        System.out.println(dog.hashCode());
        System.out.println(map.get(dog));
        Dog dog1 = new Dog();
        System.out.println(dog1.hashCode());
        System.out.println(map.get(dog1));
    }

}
