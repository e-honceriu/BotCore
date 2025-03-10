package com.dimetro.discordbot.musicservice.handler.searcher.core.exception;

import org.springframework.http.HttpStatus;

public class WrongSearchDataTypeException extends SearcherCoreException {

    public WrongSearchDataTypeException(Class<?> expectedType, Object received) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format(
                "Invalid Search data type received, Expected type: %s, but received: %s - %s",
                expectedType.getName(),
                received.getClass().getName(),
                received.toString()
            ),
            "Could not search for song(s)."
        );
    }
    
}
