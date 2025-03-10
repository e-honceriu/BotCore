package com.dimetro.discordbot.musicservice.handler.downloader.spotify.exception;

import java.io.File;

import org.springframework.http.HttpStatus;

public class NoTempFileFoundException extends SpotifyDownloaderException {
    
    public NoTempFileFoundException(File file) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("No temp file: %s found!", file.getAbsolutePath()),
            "An error has occurred, could not download the song!"
        );
    }

}
