package com.dimetro.discordbot.musicservice.song.service.stream.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class StreamServiceException extends MusicServiceException {
    
    public StreamServiceException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public StreamServiceException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}