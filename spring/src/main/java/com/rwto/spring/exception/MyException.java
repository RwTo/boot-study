package com.rwto.spring.exception;

import lombok.Data;

/**
 * @author renmw
 * @create 2024/9/6 11:17
 **/
@Data
public class MyException extends RuntimeException{
    private String errorNo;
    private String errorMsg;
}
