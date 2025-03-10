package com.dimetro.discordbot.game_service.lol.exception;
import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.core.exception.GameServiceException;

public class LolGameServiceException extends GameServiceException {

    public LolGameServiceException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public LolGameServiceException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }

}
