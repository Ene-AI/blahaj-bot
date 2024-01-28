package com.blahaj.Blahajbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blahaj.Blahajbot.entity.GuildConfig;
import java.util.List;


@Repository
public interface GuildConfigRepository extends JpaRepository<GuildConfig, String>{
    List<GuildConfig> findByGuildId(String guildId);
}
