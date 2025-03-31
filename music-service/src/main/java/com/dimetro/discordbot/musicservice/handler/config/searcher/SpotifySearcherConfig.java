package com.dimetro.discordbot.musicservice.handler.config.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifySearcherConfig extends SearcherConfig {
    
    private final String clientId;
    private final String clientSecret;
    private final Integer credentialsTimeout;

    @Autowired
    public SpotifySearcherConfig(
        @Value("${spotify.client-id}") String clientId, 
        @Value("${spotify.client-secret}") String clientSecret,
        @Value("${spotify.credentials-timeout}") Integer credentialsTimeout
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.credentialsTimeout = credentialsTimeout;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Integer getCredentialsTimeout() {
        return credentialsTimeout;
    }
    
}
