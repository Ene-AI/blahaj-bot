package com.blahaj.Blahajbot.service;


import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.util.TimerUtils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public void schedule(final ScheduledMessage scheduledMessage) {
        final JobDetail jobDetail = TimerUtils.buildJobDetail(scheduledMessage);
        final Trigger trigger = TimerUtils.buildTrigger(scheduledMessage);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public boolean checkScheduledJobExists(long scheduledMessageId){
        try{
            return scheduler.checkExists(new JobKey(""+scheduledMessageId));
        } catch(SchedulerException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public void deleteScheduledJob(long scheduledMessageId){
        try{
            scheduler.deleteJob(new JobKey(""+scheduledMessageId));
        } catch(SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
        
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        }
        catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
