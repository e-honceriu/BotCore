package com.dimetro.discordbot.game_service.core.exception;

import org.springframework.http.HttpStatus;

public class UnknownException extends GameServiceException {

    public UnknownException(Exception exception) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An unknown error has occured!",
            "An unknown error has occurred: " + exception.getMessage(),
            exception
        );
    }
    
}
