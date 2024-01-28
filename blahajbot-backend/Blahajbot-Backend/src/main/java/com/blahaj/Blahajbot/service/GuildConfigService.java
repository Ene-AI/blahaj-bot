package com.blahaj.Blahajbot.service;

import org.springframework.stereotype.Service;

import com.blahaj.Blahajbot.entity.GuildConfig;

@Service
public interface GuildConfigService {
    public GuildConfig addGuildConfig(String guildId);
    public GuildConfig getGuildConfig(String guildId);
    public void deleteGuildConfig(String guildId);
    public GuildConfig updateGuildConfig(GuildConfig guildConfig);
}
