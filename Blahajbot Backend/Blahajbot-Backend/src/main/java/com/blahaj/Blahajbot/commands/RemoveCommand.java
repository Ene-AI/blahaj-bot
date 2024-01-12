package com.blahaj.Blahajbot.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.service.ScheduledMessageService;
import com.blahaj.Blahajbot.service.ScheduledMessageServiceImpl;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

@Component
public class RemoveCommand implements SlashCommand{
    @Autowired
    private final ScheduledMessageService scheduledMessageService;

    public RemoveCommand(ScheduledMessageServiceImpl scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @Override
    public String getName() {
        return "removejob";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String guildId = event.getInteraction().getGuild().toString();
        long id = event.getOption("id")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asLong)
            .get();
        try{
            if(scheduledMessageService.getScheduledMessageById(id).getGuildId().equals(guildId)){
                scheduledMessageService.deleteScheduledMessage(id);;

                return event.reply()
                    .withEphemeral(true)
                    .withContent("Message has been deleted!");
            }
        }
        catch(Exception e){
                return event.reply()
                    .withEphemeral(true)
                    .withContent("There was an issue deleting this message");
        }
        return null;
    }
}
