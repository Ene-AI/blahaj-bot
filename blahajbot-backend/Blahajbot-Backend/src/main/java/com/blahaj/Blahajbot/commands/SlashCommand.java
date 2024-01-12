//taken from official discord4j documentation examples at https://github.com/Discord4J/example-projects/blob/master/gradle-simple-bot/src/main/java/com/novamaday/d4j/gradle/simplebot/commands/SlashCommand.java

package com.blahaj.Blahajbot.commands;


import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

/**
 * A simple interface defining our slash command class contract.
 *  a getName() method to provide the case-sensitive name of the command.
 *  and a handle() method which will house all the logic for processing each command.
 */
public interface SlashCommand {

    String getName();

    Mono<Void> handle(ChatInputInteractionEvent event);
}