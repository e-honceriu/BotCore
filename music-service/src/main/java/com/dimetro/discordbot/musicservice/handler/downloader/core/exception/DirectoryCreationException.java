package com.dimetro.discordbot.musicservice.handler.downloader.core.exception;

import org.springframework.http.HttpStatus;

public class DirectoryCreationException extends DownloaderCoreException {
    
    public DirectoryCreationException(String path) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Failed to create directory at: %s", path),
            "An error occurred while attempting to create the download directory."
        );
    }

}
