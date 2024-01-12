package com.blahaj.Blahajbot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.repository.ScheduledMessageRepository;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;

@Component
public class MessageJobUtils {
    @Autowired
    public GatewayDiscordClient gatewayDiscordClient;
    @Autowired
    public ScheduledMessageRepository repository;

    public MessageJobUtils(GatewayDiscordClient gatewayDiscordClient, ScheduledMessageRepository repository) {
        this.gatewayDiscordClient = gatewayDiscordClient;
        this.repository = repository;
    }

    public void sendMessage(Long scheduledMessageId){
        ScheduledMessage scheduledMessage = repository.findById(scheduledMessageId).get();
        gatewayDiscordClient.getChannelById(Snowflake.of(scheduledMessage.getChannelId()))
            .ofType(GuildMessageChannel.class)
            .flatMap(channel -> channel.createMessage(scheduledMessage.getMessage()))
            .subscribe();
    }

}
