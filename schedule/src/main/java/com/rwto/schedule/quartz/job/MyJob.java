package com.rwto.schedule.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author renmw
 * @create 2024/2/27 11:28
 **/
@Slf4j
public class MyJob implements Job {

    private String name;

    /**jobDataMap 会调用set方法设置 Job的自定义属性*/
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //通过getJobDataMap方法获取JobDetail所提供的数据
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();//key相同会覆盖
        log.info("jobDetail:{}",jobDataMap.get("data"));
        log.info("trigger JobDataMap:{}",context.getTrigger().getJobDataMap());
        log.info("MyJob:{} this timestamp is {}", name, System.currentTimeMillis()/1000);
    }
}
