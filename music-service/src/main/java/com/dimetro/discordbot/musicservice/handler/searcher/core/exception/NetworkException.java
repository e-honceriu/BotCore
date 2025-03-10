package com.dimetro.discordbot.musicservice.handler.searcher.core.exception;

import org.springframework.http.HttpStatus;

public class NetworkException extends SearcherCoreException {

    public NetworkException(Throwable cause) {
        super(
            HttpStatus.SERVICE_UNAVAILABLE,
            String.format("A network error has occurred: %s", cause.getMessage()),
            "A network error has occurred, could not search for song(s).",
            cause
        );
    }
    
}
