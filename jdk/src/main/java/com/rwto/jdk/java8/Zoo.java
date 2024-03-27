package com.rwto.jdk.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Zoo {
   private Dog dog;

   public static Zoo getZoo() {
      return Zoo.builder().dog(Dog.builder().age(1).build()).build();
   }
}


