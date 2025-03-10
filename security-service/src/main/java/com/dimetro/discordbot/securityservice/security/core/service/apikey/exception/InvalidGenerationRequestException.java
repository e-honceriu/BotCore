package com.dimetro.discordbot.securityservice.security.core.service.apikey.exception;

import org.springframework.http.HttpStatus;

public class InvalidGenerationRequestException extends ApiKeyServiceException {

    public InvalidGenerationRequestException() {
        super(
            HttpStatus.BAD_REQUEST,
            "Invalid api key request generation received!",
            "Invalid api key request generation received!"
        );
    }

}
    

