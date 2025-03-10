package com.dimetro.discordbot.musicservice.handler.downloader.spotify.exception;

import java.io.File;

import org.springframework.http.HttpStatus;

public class InvalidTempDirException extends SpotifyDownloaderException {
    
    public InvalidTempDirException(File file) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Invalid temp directory: %s found!", file.getAbsolutePath()),
            "An error has occurred, could not download the song!"
        );
    }

}
