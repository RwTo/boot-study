package com.rwto.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renmw
 * @create 2024/9/6 11:16
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/filter")
    public String filter(){
        return "hello filter";
    }

    @RequestMapping("/interceptor")
    public String interceptor(){
        return "hello interceptor";
    }
}
