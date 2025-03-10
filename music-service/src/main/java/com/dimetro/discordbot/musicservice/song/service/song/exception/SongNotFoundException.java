package com.dimetro.discordbot.musicservice.song.service.song.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.ExternalId;

public class SongNotFoundException extends SongServiceException {
    
    public SongNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Song (ID=%s) not found.", id),
            "Song not found!"
        );
    }

    public SongNotFoundException(ExternalId externalId) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Song (PLATFORM=%s) (EXTERNAL_ID=%s) not found.", externalId.getPlatform(), externalId.getExternalId()),
            "Song not found!"
        );
    }

}
