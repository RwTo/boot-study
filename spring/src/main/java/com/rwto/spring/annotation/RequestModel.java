package com.rwto.spring.annotation;

import java.lang.annotation.*;

/**
 * @author renmw
 * @create 2024/9/6 11:37
 **/
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestModel {
}
