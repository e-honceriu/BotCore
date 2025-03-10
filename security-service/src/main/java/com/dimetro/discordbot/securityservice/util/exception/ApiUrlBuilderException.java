package com.dimetro.discordbot.securityservice.util.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

public class ApiUrlBuilderException extends SecurityServiceException {
    
    public ApiUrlBuilderException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public ApiUrlBuilderException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
