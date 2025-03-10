package com.dimetro.discordbot.musicservice.handler.searcher.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidSearchDataTypeException extends SearcherCoreException {

    public InvalidSearchDataTypeException(Class<?> expected, Class<?> received){
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Expected: %s but received: %s", expected.getName(), received.getName()),
            "Song(s) not found"
        );
    }
    
}
