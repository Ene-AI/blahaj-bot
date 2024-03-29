package com.blahaj.Blahajbot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BlahajbotBackendApplication {
    @Value("${token}")
    String token;
    public static void main(String[] args) {
        //Start spring application
        new SpringApplicationBuilder(BlahajbotBackendApplication.class)
            .build()
            .run(args);
    }


    @Bean
    GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(token).build()
            .gateway()
            .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.listening("to /commands")))
            .login()
            .block();
    }

    @Bean
    RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }
}