package com.dimetro.discordbot.game_service.lol.roster.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class RosterException extends LolGameServiceException {
    
    public RosterException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public RosterException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }

}
