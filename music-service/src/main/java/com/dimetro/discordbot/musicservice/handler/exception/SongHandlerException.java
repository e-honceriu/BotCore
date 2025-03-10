package com.dimetro.discordbot.musicservice.handler.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class SongHandlerException extends MusicServiceException {
    
    public SongHandlerException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public SongHandlerException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
