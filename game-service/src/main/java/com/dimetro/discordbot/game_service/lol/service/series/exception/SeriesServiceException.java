package com.dimetro.discordbot.game_service.lol.service.series.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class SeriesServiceException extends LolGameServiceException {

    public SeriesServiceException(HttpStatus status, String userMessage, String developerMessage) {
        super(status, userMessage, developerMessage);
    }

    public SeriesServiceException(HttpStatus status, String userMessage, String developerMessage, Throwable cause) {
        super(status, userMessage, developerMessage, cause);
    }
}
