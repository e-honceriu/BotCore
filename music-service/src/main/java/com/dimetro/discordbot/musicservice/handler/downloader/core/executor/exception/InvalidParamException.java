package com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception;

import org.springframework.http.HttpStatus;

public class InvalidParamException extends DownloadExecutorException {

    public InvalidParamException(String param) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Invalid param found in DownloadExecutor: %s", param),
            "Failed to download song(s)."
        );
    }
    
}
