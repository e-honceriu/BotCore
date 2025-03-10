package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class MatchServiceException extends LolGameServiceException {

    public MatchServiceException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public MatchServiceException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }
    
}
