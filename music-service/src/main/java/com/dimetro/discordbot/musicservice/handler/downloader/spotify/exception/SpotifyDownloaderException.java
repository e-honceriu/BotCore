package com.dimetro.discordbot.musicservice.handler.downloader.spotify.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class SpotifyDownloaderException extends MusicServiceException {
    
    public SpotifyDownloaderException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public SpotifyDownloaderException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
