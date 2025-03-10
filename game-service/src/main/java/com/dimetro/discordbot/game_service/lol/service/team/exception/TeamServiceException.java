package com.dimetro.discordbot.game_service.lol.service.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class TeamServiceException extends LolGameServiceException {
    
    public TeamServiceException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public TeamServiceException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }

}
