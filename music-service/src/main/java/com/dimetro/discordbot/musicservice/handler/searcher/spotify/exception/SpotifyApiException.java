package com.dimetro.discordbot.musicservice.handler.searcher.spotify.exception;

import org.springframework.http.HttpStatus;

public class SpotifyApiException extends SpotifySearcherException {
    
    public SpotifyApiException(Throwable cause) {
        super(
            HttpStatus.SERVICE_UNAVAILABLE,
            String.format("Failed to make request to Spotify API: %s", cause.getMessage()),
            "Failed to retrieve song(s) from Spotify!",
            cause
        );
    }

}
