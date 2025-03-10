package com.dimetro.discordbot.musicservice.handler.searcher.core.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedSearchDataConversionException extends SearcherCoreException {

    public UnsupportedSearchDataConversionException (Class<?> type) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Conversion of: %s is not supported!", type.getName()),
            "Could not search the song(s)."
        );
    }
    
}
