package com.blahaj.Blahajbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import java.util.List;


@Repository
public interface ScheduledMessageRepository 
    extends JpaRepository<ScheduledMessage, Long>{
        List<ScheduledMessage> findById(long id);
        List<ScheduledMessage> findByGuildId(long guildId);
}
