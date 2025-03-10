package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class UnknownKindException extends YoutubeSearcherException {

    public UnknownKindException(String kind) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Unknown Youtube search kind found: %s", kind),
            "Could not search for song(s)."
        );
    }
    
}
