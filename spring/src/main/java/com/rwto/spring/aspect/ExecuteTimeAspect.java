package com.rwto.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author renmw
 * @create 2023/12/14 14:00
 **/
@Aspect
@Slf4j
@Component
public class ExecuteTimeAspect {
    //切点:决定用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
    @Pointcut("@annotation(com.rwto.spring.annotation.ExecuteTimeLog)")
    private void ExecuteTimePointCut(){

    }


    @Around("ExecuteTimePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("ExecuteTimeLog: class:"+className+" method:"+methodName + " ExecuteTime:"+time+"ms");
        return result;
    }


}
