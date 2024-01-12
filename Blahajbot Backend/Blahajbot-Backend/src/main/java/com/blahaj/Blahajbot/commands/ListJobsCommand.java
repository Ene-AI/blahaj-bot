package com.blahaj.Blahajbot.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.ScheduledMessageService;

import de.vandermeer.asciitable.AsciiTable;
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
        String guildId = event.getInteraction().getGuild().toString();
        List<ScheduledMessage> guildMessages = scheduledMessageService.getScheduledMessagesByGuild(guildId);
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Label", "Message", "Cron");
        at.addRule();
        for(ScheduledMessage message : guildMessages){
            at.addRow(message.getLabel(), message.getMessage(), message.generateCron());
            at.addRule();
        }
        String outputTable = at.render();
        return  event.reply()
            .withContent(outputTable);
    }
}
