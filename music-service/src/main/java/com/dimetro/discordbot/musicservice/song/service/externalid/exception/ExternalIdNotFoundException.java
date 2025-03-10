package com.dimetro.discordbot.musicservice.song.service.externalid.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

public class ExternalIdNotFoundException extends ExternalIdServiceException {

    public ExternalIdNotFoundException(SongPlatform platform, String id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("External Id not found for PLATFORM=%s ID=%s.", platform, id),
            "Could not find external id."
        );
    }
    
}
