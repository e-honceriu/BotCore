package com.dimetro.discordbot.securityservice.security.authentication.apikey.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

public class ApiKeyAuthenticationServiceException extends SecurityServiceException {
    
    public ApiKeyAuthenticationServiceException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public ApiKeyAuthenticationServiceException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}