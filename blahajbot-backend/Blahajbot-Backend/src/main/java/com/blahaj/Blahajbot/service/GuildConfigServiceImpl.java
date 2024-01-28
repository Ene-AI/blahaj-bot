package com.blahaj.Blahajbot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blahaj.Blahajbot.entity.GuildConfig;
import com.blahaj.Blahajbot.repository.GuildConfigRepository;

@Service
public class GuildConfigServiceImpl implements GuildConfigService{

    @Autowired
    private GuildConfigRepository guildConfigRepository;

    public GuildConfigServiceImpl(GuildConfigRepository guildConfigRepository){
        this.guildConfigRepository = guildConfigRepository;
    }

    @Override
    public GuildConfig addGuildConfig(String guildId) {
        GuildConfig newConfig = new GuildConfig(guildId);
        guildConfigRepository.save(newConfig);
        return newConfig;
    }

    @Override
    public GuildConfig getGuildConfig(String guildId) {
        ArrayList<GuildConfig> guildConfigs = new ArrayList<>(guildConfigRepository.findByGuildId(guildId));
        if(guildConfigs.size() == 0){
            return addGuildConfig(guildId);
        }
        return guildConfigs.get(0);
    }

    @Override
    public void deleteGuildConfig(String guildId) {
        guildConfigRepository.deleteById(guildId);
    }

    @Override
    public GuildConfig updateGuildConfig(GuildConfig guildConfig) {
        return guildConfigRepository.save(guildConfig);
    }
    
}
