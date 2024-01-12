package com.blahaj.Blahajbot.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blahaj.Blahajbot.entity.ScheduledMessage;
import com.blahaj.Blahajbot.service.ScheduledMessageService;
import com.blahaj.Blahajbot.service.ScheduledMessageServiceImpl;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

@Component
public class UpdateCommand implements SlashCommand{
    @Autowired
    private final ScheduledMessageService scheduledMessageService;

    public UpdateCommand(ScheduledMessageServiceImpl scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @Override
    public String getName() {
        return "updatejob";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String guildId = event.getInteraction().getGuild().toString();
        long id = event.getOption("id")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asLong)
            .get();
        String label = event.getOption("label")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .get();
        String channelId = event.getOption("channel")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asChannel)
            .get()
            .block()
            .getId()
            .asString(); 
        String message = event.getOption("message")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .get();
        boolean repeat = event.getOption("repeats")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asBoolean)
            .get();
        String month = event.getOption("month")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("*");
        String dayOfWeek = event.getOption("dayofweek")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("?");
        String dayOfMonth = event.getOption("dayofmonth")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("?");
        String hour = event.getOption("hour")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("*");
        String minute = event.getOption("minute")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("*");
        try{
            ScheduledMessage scheduledMessage = new ScheduledMessage(guildId, channelId, label, message, repeat, minute, hour, dayOfMonth, month, dayOfWeek);
            scheduledMessage.setId(id);
            scheduledMessageService.updateScheduledMessage(scheduledMessage);

            return event.reply()
                .withEphemeral(true)
                .withContent("Message has been updated!");
        }
        catch(Exception e){
            
            return event.reply()
                .withEphemeral(true)
                .withContent("There was an issue updating this message");
        }
    }
}
