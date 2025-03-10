package com.dimetro.discordbot.musicservice.handler.searcher.core.exception;

import org.springframework.http.HttpStatus;

public class SongNotFoundException extends SearcherCoreException {

    public SongNotFoundException(String titleId){
        super(
            HttpStatus.NOT_FOUND,
            String.format("Song (ID/TITLE=%s) not found!", titleId),
            "Song(s) not found"
        );
    }
    
}
