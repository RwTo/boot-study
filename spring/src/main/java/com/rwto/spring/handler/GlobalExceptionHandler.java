package com.rwto.spring.handler;

import com.rwto.common.domain.enums.ErrNo;
import com.rwto.common.domain.resp.Result;
import com.rwto.spring.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MyException.class})
    public Object exceptionHandler(HttpServletRequest request, MyException e){
        return Result.fail(e.getErrorNo(),e.getErrorMsg());
    }


    @ExceptionHandler(value = {Exception.class})
    public Object exceptionHandler(HttpServletRequest request, Exception e){
        log.error("未知异常，请求地址：{}", request.getRequestURI(),e);
        return Result.fail(ErrNo.ERROR_INTERNAL);
    }
}