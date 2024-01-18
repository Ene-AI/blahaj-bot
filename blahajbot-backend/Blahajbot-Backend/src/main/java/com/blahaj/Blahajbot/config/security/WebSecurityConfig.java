package com.blahaj.Blahajbot.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.blahaj.Blahajbot.config.security.oauth2.DiscordOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer{

    private final DiscordOAuth2UserService oAuth2UserService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final CsrfTokenRequestAttributeHandler csrfRequestHandler = new CsrfTokenRequestAttributeHandler();
        csrfRequestHandler.setCsrfRequestAttributeName(null);

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((auths) -> 
                    auths.requestMatchers("/discord/callback").authenticated()
                    .anyRequest().permitAll()
                )
                .logout(logout -> logout.logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpStatus.OK.value())))
                .oauth2Login(oauth2 -> {
                    oauth2.userInfoEndpoint(user -> user.userService(this.oAuth2UserService));
                    oauth2.defaultSuccessUrl("http://localhost:5173/", true);
                    oauth2.permitAll();
                });

                return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE")
            .allowCredentials(true);
    }
}