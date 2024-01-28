package com.blahaj.Blahajbot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guild_configs")
public class GuildConfig {

    @Id
    @Column(name = "guild_id")
    private String guildId;
    private boolean useScheduledMessages;

    public GuildConfig(String guildId){
        this.guildId = guildId;
    }
}
