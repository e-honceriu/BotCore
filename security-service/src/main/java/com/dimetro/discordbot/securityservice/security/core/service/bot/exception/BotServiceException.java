package com.dimetro.discordbot.securityservice.security.core.service.bot.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

public class BotServiceException extends SecurityServiceException {
    
    public BotServiceException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public BotServiceException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
