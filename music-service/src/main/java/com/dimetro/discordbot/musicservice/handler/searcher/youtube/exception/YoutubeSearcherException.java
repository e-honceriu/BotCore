package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;


import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class YoutubeSearcherException extends MusicServiceException {
    
    public YoutubeSearcherException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public YoutubeSearcherException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
