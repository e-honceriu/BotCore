package com.dimetro.discordbot.musicservice.song.service.song.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

public class SongAlreadyExistsException extends SongServiceException {
    
    public SongAlreadyExistsException(SongPlatform platform, String externalId) {
        super(
            HttpStatus.CONFLICT,
            String.format("Song (PLATFORM=%s) (EXTERNAL_ID=%s) already exists.", platform, externalId),
            "Song already exists!"
        );
    }

}
