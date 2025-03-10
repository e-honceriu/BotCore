package com.dimetro.discordbot.musicservice.handler.downloader.spotify.exception;

import java.io.File;

import org.springframework.http.HttpStatus;

public class TempFileMoveException extends SpotifyDownloaderException {
    
    public TempFileMoveException(File tempSong, File outputSong) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Could not move file from: %s to: %s", tempSong.getAbsolutePath(), outputSong.getAbsolutePath()),
            "An error has occurred, could not download the song!"
        );
    }

}
