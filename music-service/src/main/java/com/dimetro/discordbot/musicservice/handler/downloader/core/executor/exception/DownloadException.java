package com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception;

import org.springframework.http.HttpStatus;

public class DownloadException extends DownloadExecutorException {

    public DownloadException(String err) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Failed to download song, error: %s", err),
            "Failed to download song(s)."
        );
    }
    
    public DownloadException(Throwable cause) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("An error occurred while downloading song: %s", cause.getMessage()),
            "Failed to download song(s).",
            cause
        );
    }
    
}
