/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedular;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author kasturi Novasoft
 */
public class CronTrigger {

    //public static void main(String[] args) throws InterruptedException {
    public static Scheduler callTrigger() {
        Scheduler sch = null;
        try {

            JobDetail job = JobBuilder.newJob(RestJob.class).withIdentity("restJob").build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).forJob("restJob").build();

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            sch = schFactory.getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);
            return sch;

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return sch;
    }
}
