package com.dimetro.discordbot.musicservice.song.service.download.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.Song;

public class DownloadNotFoundException extends DownloadServiceException {
    
    public DownloadNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Download with ID=%s not found.", id),
            "An error has occurred!" 
        );
    }

    public DownloadNotFoundException(Song song) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Download for song (ID=%s) not found.", song.getId()),
            "An error has occurred!" 
        );
    }

}
