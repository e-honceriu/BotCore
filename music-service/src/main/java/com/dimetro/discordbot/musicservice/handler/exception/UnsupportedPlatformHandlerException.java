package com.dimetro.discordbot.musicservice.handler.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

public class UnsupportedPlatformHandlerException extends SongHandlerException {

    public UnsupportedPlatformHandlerException(SongPlatform platform) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("No handler found for platform: %s", platform),
            "An error occurred, could not process the request."
        );
    }
    
}
