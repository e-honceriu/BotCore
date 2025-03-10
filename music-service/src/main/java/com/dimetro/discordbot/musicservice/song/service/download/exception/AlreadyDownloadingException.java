package com.dimetro.discordbot.musicservice.song.service.download.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.Song;

public class AlreadyDownloadingException extends DownloadServiceException {

    public AlreadyDownloadingException(Song song) {
        super(
            HttpStatus.CONFLICT,
            String.format("Download already active for song (ID=%s).", song.getId()),
            "Song audio file already downloading!" 
        );
    }

}
