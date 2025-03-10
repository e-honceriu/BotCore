package com.dimetro.discordbot.musicservice.handler.searcher.spotify.exception;

import org.springframework.http.HttpStatus;

public class DataParsingException extends SpotifySearcherException {

    public DataParsingException(Throwable cause) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Could not parse response of request: %s", cause.getMessage()),
            "Could not decode response from Spotify.",
            cause
        );
    }
    
}
