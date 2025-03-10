package com.dimetro.discordbot.musicservice.song.service.externalid.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class ExternalIdServiceException extends MusicServiceException {
    
    public ExternalIdServiceException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public ExternalIdServiceException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
