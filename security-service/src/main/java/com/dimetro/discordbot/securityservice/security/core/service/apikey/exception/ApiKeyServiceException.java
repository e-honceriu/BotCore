package com.dimetro.discordbot.securityservice.security.core.service.apikey.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

public class ApiKeyServiceException extends SecurityServiceException {
    
    public ApiKeyServiceException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public ApiKeyServiceException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
