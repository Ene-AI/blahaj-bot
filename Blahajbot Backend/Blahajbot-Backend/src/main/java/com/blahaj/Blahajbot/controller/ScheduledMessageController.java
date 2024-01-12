package com.blahaj.Blahajbot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.ScheduledMessageServiceImpl;

@RestController
@CrossOrigin(origins = "${frontend-url}")
public class ScheduledMessageController {
    
    @Autowired
    private final ScheduledMessageServiceImpl scheduledMessageService;

    public ScheduledMessageController(ScheduledMessageServiceImpl scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @GetMapping("/scheduled-messages")
    public List<ScheduledMessage> getScheduledMessages(){
        return scheduledMessageService.getScheduledMessages();
    }

    @GetMapping("/scheduled-message/{id}")
    public ScheduledMessage getScheduledMessage(@PathVariable("id") long id) {
        return scheduledMessageService.getScheduledMessageById(id);
    }

    @PostMapping("/add")
    public void addScheduledMessage(@RequestBody ScheduledMessage scheduledMessage) {
        scheduledMessageService.addScheduledMessage(scheduledMessage);
    }

    @DeleteMapping("/scheduled-message/{id}")
    public String deleteScheduledMessage(@PathVariable("id") long id){
        scheduledMessageService.deleteScheduledMessage(id);
        return "Scheduled Message Deleted";
    }
    
    @PutMapping("/scheduled-message")
    public ScheduledMessage updatScheduledMessage(@RequestBody ScheduledMessage scheduledMessage){
        return scheduledMessageService.updateScheduledMessage(scheduledMessage);
    }
}
