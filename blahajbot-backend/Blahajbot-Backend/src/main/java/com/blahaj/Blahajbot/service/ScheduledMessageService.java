package com.blahaj.Blahajbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blahaj.Blahajbot.entity.ScheduledMessage;

@Service
public interface ScheduledMessageService {
    public ScheduledMessage addScheduledMessage(ScheduledMessage scheduledMessage);
    public ScheduledMessage getScheduledMessageById(long id);
    public List<ScheduledMessage> getScheduledMessages(String guildId);
    public void deleteScheduledMessage(long id);
    public ScheduledMessage updateScheduledMessage(ScheduledMessage scheduledMessage);
}
