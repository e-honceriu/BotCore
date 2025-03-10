package com.dimetro.discordbot.musicservice.song.service.stream.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class StreamRequestNotFoundException extends StreamServiceException {
    
    public StreamRequestNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Stream Request (ID=%s) not found.", id),
            "Stream request not found!" 
        );
    }
    
}
