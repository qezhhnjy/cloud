package com.fzyszsz.quartzdemo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author fuzy
 */
@SpringBootApplication
@Slf4j
public class QuartzDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzDemoApplication.class, args);

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-1", "group-1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();

            JobDetail job = JobBuilder.newJob(HelloQuartz.class)
                    .withIdentity("job-1", "group-1")
                    .usingJobData("name", "quartz")
                    .build();

            scheduler.scheduleJob(job, trigger);

            scheduler.start();

            TimeUnit.SECONDS.sleep(10);
            scheduler.shutdown(true);

        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

