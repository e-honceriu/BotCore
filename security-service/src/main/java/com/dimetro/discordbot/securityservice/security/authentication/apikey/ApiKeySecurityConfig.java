package com.dimetro.discordbot.securityservice.security.authentication.apikey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApiKeySecurityConfig  {
    
    private final ApiKeyAuthenticationService authService;

    private static final String[] PUBLIC_ENDPOINTS = {
        "/api/bot", 
        "/api/bot/generate-api-key",
        "/error"
    };

    @Autowired
    public ApiKeySecurityConfig(ApiKeyAuthenticationService authService) {
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ApiKeyAuthenticationFilter authFilter = new ApiKeyAuthenticationFilter(authService, PUBLIC_ENDPOINTS);
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);  
        return http.build();
    }

}
