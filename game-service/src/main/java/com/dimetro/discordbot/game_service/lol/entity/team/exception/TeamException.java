package com.dimetro.discordbot.game_service.lol.entity.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class TeamException extends LolGameServiceException {

    public TeamException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public TeamException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }
    
}
