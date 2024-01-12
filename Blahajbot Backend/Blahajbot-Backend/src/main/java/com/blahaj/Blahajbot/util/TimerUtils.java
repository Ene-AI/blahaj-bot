package com.blahaj.Blahajbot.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.job.MessageJob;

public class TimerUtils {
    private TimerUtils() {}
    
    public static JobDetail buildJobDetail(final ScheduledMessage scheduledMessage) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JOB_ID", scheduledMessage.getId());

        return JobBuilder
                .newJob(MessageJob.class)
                .withIdentity(""+scheduledMessage.getId())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final ScheduledMessage scheduledMessage) {
        
        if(scheduledMessage.isRepeat()) {
            return TriggerBuilder
                .newTrigger()
                .withIdentity(""+scheduledMessage.getId())
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledMessage.generateCron()))
                .build();
        } else {
            return TriggerBuilder
                .newTrigger()
                .withIdentity(""+scheduledMessage.getId())
                .startAt(scheduledMessage.generateDate())
                .build();
        }
    }
}
