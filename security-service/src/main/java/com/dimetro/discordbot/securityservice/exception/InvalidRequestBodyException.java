package com.dimetro.discordbot.securityservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestBodyException extends SecurityServiceException {

    public InvalidRequestBodyException() {
        super(
            HttpStatus.BAD_REQUEST,
            "Invalid data received!",
            "Invalid request body format received!"
        );
    }
    
}
