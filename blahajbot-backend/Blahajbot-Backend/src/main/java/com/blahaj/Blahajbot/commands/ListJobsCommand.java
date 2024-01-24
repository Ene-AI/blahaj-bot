package com.blahaj.Blahajbot.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.ScheduledMessageService;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

@Component
public class ListJobsCommand implements SlashCommand{
    @Autowired
    private final ScheduledMessageService scheduledMessageService;

    public ListJobsCommand(ScheduledMessageService scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @Override
    public String getName() {
        return "listjobs";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String guildId = event.getInteraction().getGuild().block().getId().asString();
        List<ScheduledMessage> guildMessages = scheduledMessageService.getScheduledMessages(guildId);
        String output = "Id, Label, Cron, Message:";
        for(ScheduledMessage s : guildMessages){
            output += "\n" + s.getId() + ", " + s.getLabel() + ", " + s.generateCron() + ", " + s.getMessage();
        }
        return  event.reply()
            .withContent(output);
    }
}
