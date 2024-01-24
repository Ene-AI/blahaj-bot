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
public class ScheduleOneTimeCommand implements SlashCommand{
    @Autowired
    private final ScheduledMessageService scheduledMessageService;

    public ScheduleOneTimeCommand(ScheduledMessageServiceImpl scheduledMessageService) {
        this.scheduledMessageService = scheduledMessageService;
    }

    @Override
    public String getName() {
        return "scheduleonetimejob";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String guildId = event.getInteraction().getGuild().block().getId().asString();
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
        String month = event.getOption("month")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .orElse("*");
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
        double year = event.getOption("minute")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asDouble)
            .orElse((double)0);
        try{
            ScheduledMessage scheduledMessage = new ScheduledMessage(guildId, channelId, label, message, false, minute, hour, dayOfMonth, month, "?", year);
            scheduledMessageService.addScheduledMessage(scheduledMessage);

            return event.reply()
                .withEphemeral(true)
                .withContent("New Scheduled Message Created!");
        }
        catch(Exception e){
            return event.reply()
                .withEphemeral(true)
                .withContent("There was an issue creating this message");
        }
    }
}
