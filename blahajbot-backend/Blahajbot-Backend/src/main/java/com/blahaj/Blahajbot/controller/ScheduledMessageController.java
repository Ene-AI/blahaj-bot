package com.blahaj.Blahajbot.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blahaj.Blahajbot.config.security.oauth2.DiscordOAuth2User;
import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.ScheduledMessageServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
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

    @GetMapping("users/@me")
    public ResponseEntity<?> me(@AuthenticationPrincipal DiscordOAuth2User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "unauthorized"));
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/discord/callback")
    public void DiscordLogin(HttpServletResponse response) throws IOException{
        response.sendRedirect("http://localhost:5173");
    }
}
