package com.blahaj.Blahajbot.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.blahaj.Blahajbot.entity.GuildConfig;
import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.GuildConfigService;
import com.blahaj.Blahajbot.service.ScheduledMessageServiceImpl;
import com.blahaj.Blahajbot.util.GuildUtils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ScheduledMessageController {
    
    @Autowired
    private final ScheduledMessageServiceImpl scheduledMessageService;
    @Autowired
    private final GuildConfigService guildConfigService;
    @Autowired
    private final GuildUtils guildUtils;

    @Value("${frontend-url}")
    private String frontEndURL;
    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    private String clientId;

    public ScheduledMessageController(ScheduledMessageServiceImpl scheduledMessageService, GuildConfigService guildConfigService,GuildUtils guildUtils) {
        this.scheduledMessageService = scheduledMessageService;
        this.guildUtils = guildUtils;
        this.guildConfigService = guildConfigService;
    }

    @GetMapping("{guildId}/config")
    public GuildConfig getGuildConfig(@PathVariable("guildId") String guildId){
        return guildConfigService.getGuildConfig(guildId);
    }

    @PutMapping("{guildId}/config")
    public GuildConfig updateGuildConfig(@RequestBody GuildConfig guildConfig){
        return guildConfigService.updateGuildConfig(guildConfig);
    }

    @GetMapping("{guildId}/scheduled_messages")
    public List<ScheduledMessage> getScheduledMessages(@PathVariable("guildId") String guildId){
        return scheduledMessageService.getScheduledMessages(guildId);
    }

    @GetMapping("{guildId}/scheduled_message/{id}")
    public ScheduledMessage getScheduledMessage(@PathVariable("id") long id) {
        return scheduledMessageService.getScheduledMessageById(id);
    }

    @PostMapping("{guildId}/add")
    public void addScheduledMessage(@RequestBody ScheduledMessage scheduledMessage) {
        scheduledMessageService.addScheduledMessage(scheduledMessage);
    }

    @DeleteMapping("{guildId}/scheduled_message/{id}")
    public String deleteScheduledMessage(@PathVariable("id") long id){
        scheduledMessageService.deleteScheduledMessage(id);
        return "Scheduled Message Deleted";
    }
    
    @PutMapping("{guildId}/scheduled_message")
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

    @GetMapping("botGuilds")
    public List<String> getBotGuilds(){
        System.out.println("getting bot guilds");
        return guildUtils.getBotGuilds();
    }

    @GetMapping("discord/callback")
    public void DiscordLogin(HttpServletResponse response) throws IOException{
        response.sendRedirect(frontEndURL);
    }

    @GetMapping("clientId")
    public List<String> getClientId() {
        return List.of(clientId);
    }
    
}
