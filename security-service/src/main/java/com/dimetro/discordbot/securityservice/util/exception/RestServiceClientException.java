package com.dimetro.discordbot.securityservice.util.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

public class RestServiceClientException extends SecurityServiceException {
    
    public RestServiceClientException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public RestServiceClientException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}