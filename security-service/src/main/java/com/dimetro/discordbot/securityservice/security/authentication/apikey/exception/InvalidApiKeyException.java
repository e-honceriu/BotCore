package com.dimetro.discordbot.securityservice.security.authentication.apikey.exception;

import org.springframework.http.HttpStatus;

public class InvalidApiKeyException extends ApiKeyAuthenticationServiceException {
    
    public InvalidApiKeyException() {
        super(
            HttpStatus.FORBIDDEN,
            "Invalid API key!",
            "An error has occurred!"
        );
    }

}