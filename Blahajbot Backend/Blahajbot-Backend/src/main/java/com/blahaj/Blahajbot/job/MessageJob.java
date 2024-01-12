package com.blahaj.Blahajbot.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blahaj.Blahajbot.service.SchedulerService;
import com.blahaj.Blahajbot.util.MessageJobUtils;

public class MessageJob implements Job{

    
    @Autowired
    private MessageJobUtils messageJobUtils;
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    
    public MessageJob() {

    }

    public void execute(JobExecutionContext context){  

        JobDataMap data = context.getJobDetail().getJobDataMap();
        final long scheduledMessageId = data.getLong("JOB_ID");
        LOG.info("Executing task with scheduledMessageId: " + scheduledMessageId + " and job details: " + context.getJobDetail());
        messageJobUtils.sendMessage(scheduledMessageId);
        
    }
}
