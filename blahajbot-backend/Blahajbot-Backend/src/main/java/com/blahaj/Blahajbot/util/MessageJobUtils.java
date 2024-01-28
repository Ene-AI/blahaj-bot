package com.blahaj.Blahajbot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.GuildConfig;
import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.repository.ScheduledMessageRepository;
import com.blahaj.Blahajbot.service.GuildConfigService;
import com.blahaj.Blahajbot.service.GuildConfigServiceImpl;
import com.blahaj.Blahajbot.service.ScheduledMessageService;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;

@Component
public class MessageJobUtils {
    @Autowired
    public GatewayDiscordClient gatewayDiscordClient;
    @Autowired
    public ScheduledMessageService scheduledMessageService;
    @Autowired
    public ScheduledMessageRepository repository;
    @Autowired
    public GuildConfigServiceImpl guildConfigService;

    public MessageJobUtils(GatewayDiscordClient gatewayDiscordClient, ScheduledMessageService scheduledMessageService ,ScheduledMessageRepository repository, GuildConfigServiceImpl guildConfigService) {
        this.gatewayDiscordClient = gatewayDiscordClient;
        this.scheduledMessageService = scheduledMessageService;
        this.repository = repository;
        this.guildConfigService = guildConfigService;
    }

    public void sendMessage(Long scheduledMessageId){
        ScheduledMessage scheduledMessage = repository.findById(scheduledMessageId).get();
        if(guildConfigService.getGuildConfig(scheduledMessage.getGuildId()).isUseScheduledMessages()){
            gatewayDiscordClient.getChannelById(Snowflake.of(scheduledMessage.getChannelId()))
            .ofType(GuildMessageChannel.class)
            .flatMap(channel -> channel.createMessage(scheduledMessage.getMessage()))
            .subscribe();
            if(!scheduledMessage.isRepeat()){
                scheduledMessageService.deleteScheduledMessage(scheduledMessageId);
            }
        }
    }

}
