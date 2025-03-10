package com.dimetro.discordbot.musicservice.song.service.song.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.Song;

public class NoExternalIdProvidedException extends SongServiceException {
    
    public NoExternalIdProvidedException(Song song) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Song (ID=%s) has no external id provided.", song.getId()),
            "An error occurred!"
        );
    }

}
