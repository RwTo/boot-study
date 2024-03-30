package com.rwto.dubbo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author renmw
 * @create 2024/3/30 21:56
 **/
@Data
public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private String sex;
}
