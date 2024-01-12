package com.blahaj.Blahajbot.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.quartz.DateBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "scheduled_messages")
public class ScheduledMessage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "guild_id")
    private String guildId;
    @Column(name = "channel_id")
    private String channelId;
    private String label;
    private String message;
    private boolean repeat;
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;

    public ScheduledMessage(){
        
    }

    public ScheduledMessage(String guildId, String channelId, String label, String message, boolean repeat, String minute,
    String hour, String dayOfMonth, String month, String dayOfWeek) {
        this.guildId = guildId;
        this.channelId = channelId;
        this.label = label;
        this.message = message;
        this.repeat = repeat;
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        if(dayOfMonth == "?" && dayOfWeek == "?"){
            this.dayOfMonth = "*";
        }
    }

    public String generateCron(){
        List<String> cronValues = List.of("0", minute, hour, dayOfMonth, month, dayOfWeek);
        String cronStatement = String.join(" ", cronValues);
        return cronStatement;
    }

    public Date generateDate(){
        return DateBuilder.dateOf(Integer.parseInt(hour),Integer.parseInt(minute), 0,Integer.parseInt(dayOfMonth), Integer.parseInt(month));
    }
}