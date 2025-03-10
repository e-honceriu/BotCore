package com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;

public class DownloadExecutorException extends MusicServiceException {
    
    public DownloadExecutorException(HttpStatus status, String devMessage, String userMessage) {
        super(status, userMessage, devMessage);
    }

    public DownloadExecutorException(HttpStatus status, String devMessage, String userMessage, Throwable cause) {
        super(status, userMessage, devMessage, cause);
    }

}
