package com.dimetro.discordbot.musicservice.handler.searcher.spotify.exception;

import org.springframework.http.HttpStatus;

public class CredentialsException extends SpotifySearcherException {

    public CredentialsException(Throwable cause) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Could not get credentials for Spotify API: %s.", cause.getMessage()),
            "Could not connect to Spotify.",
            cause
        );
    }
    
}
