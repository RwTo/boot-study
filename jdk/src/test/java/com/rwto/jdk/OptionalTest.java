package com.rwto.jdk;

import com.rwto.jdk.java8.Dog;
import com.rwto.jdk.java8.Zoo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author renmw
 * @create 2024/3/18 13:40
 **/
@SpringBootTest
public class OptionalTest {

    @Test
    public void test01(){
        Optional.ofNullable("123").ifPresent(System.out::println);
        Optional.ofNullable(null).ifPresent(System.out::println);
    }

    @Test
    public void test02(){
        Zoo zoo = Zoo.getZoo();
        if(zoo != null){
            Dog dog = zoo.getDog();
            if(dog != null){
                int age = dog.getAge();
                System.out.println(age);
            }
        }

    }

    @Test
    public void test03(){
        Zoo zoo = Zoo.getZoo();

        Optional.ofNullable(zoo).map(o->o.getDog()).map(o->o.getAge()).ifPresent(System.out::println);

        zoo.setDog(null);
        Optional.ofNullable(zoo).map(o->o.getDog()).map(o->o.getAge()).ifPresent(System.out::println);

    }
}
