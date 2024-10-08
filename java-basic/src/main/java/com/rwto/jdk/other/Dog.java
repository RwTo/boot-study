package com.rwto.jdk.other;


import java.util.Objects;

public class Dog {
   private int age;


   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) return true;
      if (object == null || getClass() != object.getClass()) return false;
      Dog dog = (Dog) object;
      return age == dog.age;
   }

   @Override
   public int hashCode() {
      return Objects.hash(age);
   }
}