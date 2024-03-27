package com.rwto.redisson.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author renmw
 * @create 2024/3/27 15:33
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public String handleAppException(CommonException ex, HttpServletRequest request) {
        return ex.getErrorMsg();
    }
}
