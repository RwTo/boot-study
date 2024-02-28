package com.rwto.schedule.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

/**
 * @author renmw
 * @create 2024/2/26 20:19
 **/
@Component
//@EnableScheduling
@Slf4j
public class Schedule implements SchedulingConfigurer {
    /**
     * （cron = "* * * * * *")
     * {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}*/

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        /*默认是使用一个线程进行调用,这里开启线程池*/
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));

        taskRegistrar.addCronTask(()->{
            log.info("this addCronTask timestamp is "+System.currentTimeMillis()/1000);
        },"0/1 * * * * ?");

        taskRegistrar.addTriggerTask(()->{
            log.info("this addTriggerTask timestamp is "+System.currentTimeMillis()/1000);
        },triggerContext -> {
                    // 定时任务触发，可修改定时任务的执行周期
                    CronTrigger trigger = new CronTrigger("0/1 * * * * ?");
                    return trigger.nextExecutionTime(triggerContext);
                });
    }
}
