package com.mojix.job;

import com.mojix.properties.LoadProperties;
import com.mojix.properties.PropertiesController;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Paul Landaeta on 07/12/2015.
 */
public class InventoryJob {

    public void run() throws Exception {
        PropertiesController propertiesController=new PropertiesController();
        Logger log = LoggerFactory.getLogger(InventoryJob.class);

        log.info("------- Initializing -------------------");

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete --------");

        log.info("------- Scheduling Jobs ----------------");

        Date startTime = nextGivenSecondDate(null, 5);

        JobDetail job1 = newJob(TagsJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger1 = newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever()).build();

        // pass initialization parameters into the job
        Date date=new Date();
        job1.getJobDataMap().put(TagsJob.DATE_CURRENT, "INV"+date.getYear()+date.getDay()+date.getMonth());
        job1.getJobDataMap().put(TagsJob.GROUP, propertiesController.getGroup());
        job1.getJobDataMap().put(TagsJob.DATE,date.getYear()+date.getMonth() +"/"+date.getDay() +"/");

        // schedule the job to run
        Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
        log.info(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount()
                + " times, every " + trigger1.getRepeatInterval() / 1000 + " seconds");

        log.info("------- Starting Scheduler ----------------");

        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 60 seconds... -------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(60L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        log.info("------- Shutting Down ---------------------");

        sched.shutdown(true);

        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

    }

    public static void main(String[] args) throws Exception {

        InventoryJob inventoryJob = new InventoryJob();
        inventoryJob.run();
    }

}
