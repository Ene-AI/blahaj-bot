package com.blahaj.Blahajbot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.repository.ScheduledMessageRepository;

@Service
public class ScheduledMessageServiceImpl implements ScheduledMessageService{

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private final ScheduledMessageRepository scheduledMessageRepository;
    @Autowired
    private final SchedulerService schedulerService;
    
    
    public ScheduledMessageServiceImpl(ScheduledMessageRepository scheduledMessageRepository,
            SchedulerService schedulerService) {
        this.scheduledMessageRepository = scheduledMessageRepository;
        this.schedulerService = schedulerService;
    }

    @Override
    public ScheduledMessage addScheduledMessage(ScheduledMessage scheduledMessage) {
        try{
        scheduledMessage = scheduledMessageRepository.save(scheduledMessage);
        schedulerService.schedule(scheduledMessage);
        return scheduledMessage;
        }
        catch(Exception e){
            deleteScheduledMessage(scheduledMessage.getId());
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ScheduledMessage getScheduledMessageById(long id) {
        return scheduledMessageRepository.findById(id).get(0);
    }
    

    @Override
    public List<ScheduledMessage> getScheduledMessages(){
        return (List<ScheduledMessage>) scheduledMessageRepository.findAll();
    }

    @Override
    public void deleteScheduledMessage(long id) {
        if(scheduledMessageRepository.existsById(id)){
            scheduledMessageRepository.deleteById(id);
        }
        if(schedulerService.checkScheduledJobExists(id)){
            schedulerService.deleteScheduledJob(id);
        }
    }

    @Override
    public ScheduledMessage updateScheduledMessage(ScheduledMessage scheduledMessage) {
        try{
        deleteScheduledMessage(scheduledMessage.getId());
        return addScheduledMessage(scheduledMessage);
        }
        catch(Exception e){
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ScheduledMessage> getScheduledMessagesByGuild(String guildId) {
        return (List<ScheduledMessage>) scheduledMessageRepository.findByGuildId(guildId);
    }
}
