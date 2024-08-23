package com.rwto.jdk.source;

import com.rwto.jdk.other.Dog;

/**
 * @author renmw
 * @create 2024/4/28 17:02
 **/
public class HashMapStudy {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(1);
        Dog dog1 = new Dog();
        dog.setAge(1);
        System.out.println(dog.hashCode());
        System.out.println(dog1.hashCode());
        System.out.println(new Dog().hashCode());
        System.out.println(new Dog().hashCode());

        System.out.println();
    }
}
