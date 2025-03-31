package com.dimetro.discordbot.musicservice.handler.downloader.core.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class DownloaderCoreException extends MusicServiceException {
    
    public DownloaderCoreException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public DownloaderCoreException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
