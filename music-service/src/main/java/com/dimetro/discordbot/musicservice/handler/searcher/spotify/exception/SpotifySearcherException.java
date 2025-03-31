package com.dimetro.discordbot.musicservice.handler.searcher.spotify.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class SpotifySearcherException extends MusicServiceException {
    
    public SpotifySearcherException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public SpotifySearcherException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }
    
}
