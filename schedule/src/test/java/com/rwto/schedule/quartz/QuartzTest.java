package com.rwto.schedule.quartz;

import com.rwto.schedule.quartz.job.MyJob;
import com.rwto.schedule.spring.Schedule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author renmw
 * @create 2024/2/27 11:33
 **/
@Slf4j
@SpringBootTest
public class QuartzTest {

    @SneakyThrows
    @Test
    public void test01(){
        //同一个调度器 name 不能重复
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1","group1")
                .usingJobData("data","this is job")
                .usingJobData("name","myJob1")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "trigger1")
                .usingJobData("data","this is trigger")
                .usingJobData("name","myJob2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        while (true);
    }
}
