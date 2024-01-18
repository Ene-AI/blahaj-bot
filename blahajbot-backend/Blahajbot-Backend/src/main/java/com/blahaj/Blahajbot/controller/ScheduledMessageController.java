package com.blahaj.Blahajbot.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.blahaj.Blahajbot.util.GuildUtils;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ScheduledMessageController {
    
    @Autowired
    private final ScheduledMessageServiceImpl scheduledMessageService;
    @Autowired
    private final GuildUtils guildUtils;

    public ScheduledMessageController(ScheduledMessageServiceImpl scheduledMessageService, GuildUtils guildUtils) {
        this.scheduledMessageService = scheduledMessageService;
        this.guildUtils = guildUtils;
    }

    @GetMapping("{guildId}/scheduled-messages")
    public List<ScheduledMessage> getScheduledMessages(@PathVariable("guildId") long guildId){
        return scheduledMessageService.getScheduledMessages(guildId);
    }

    @GetMapping("{guildId}/scheduled-message/{id}")
    public ScheduledMessage getScheduledMessage(@PathVariable("id") long id) {
        return scheduledMessageService.getScheduledMessageById(id);
    }

    @PostMapping("{guildId}/add")
    public void addScheduledMessage(@RequestBody ScheduledMessage scheduledMessage) {
        scheduledMessageService.addScheduledMessage(scheduledMessage);
    }

    @DeleteMapping("{guildId}/scheduled-message/{id}")
    public String deleteScheduledMessage(@PathVariable("id") long id){
        scheduledMessageService.deleteScheduledMessage(id);
        return "Scheduled Message Deleted";
    }
    
    @PutMapping("{guildId}/scheduled-message")
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

    @GetMapping("botGuilds/")
    public List<String> getBotGuilds(){
        System.out.println("getting bot guilds");
        return guildUtils.getBotGuilds();
    }

    @GetMapping("discord/callback")
    public void DiscordLogin(HttpServletResponse response) throws IOException{
        response.sendRedirect("http://localhost:5173");
    }
}
