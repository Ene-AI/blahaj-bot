package com.blahaj.Blahajbot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.repository.ScheduledMessageRepository;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.object.entity.Guild;

@Component
public class GuildUtils {
    @Autowired
    public GatewayDiscordClient gatewayDiscordClient;

    public GuildUtils(GatewayDiscordClient gatewayDiscordClient) {
        this.gatewayDiscordClient = gatewayDiscordClient;
    }

    public List<String> getBotGuilds(){
        List<Guild> guilds = gatewayDiscordClient.getGuilds().collectList().block();
        List<String> guildIds = new ArrayList<String>();
        for(Guild guild : guilds){
            guildIds.add(guild.getId().asString());
        }
        return guildIds;
    }

}
