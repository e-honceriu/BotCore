package com.dimetro.discordbot.musicservice.song.service.download.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.Song;

public class MultipleActiveDownloadsException extends DownloadServiceException {

    public MultipleActiveDownloadsException(Song song) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Multiple active downloads for song (ID=%s).", song.getId()),
            "An error has occurred!" 
        );
    }
    
}
