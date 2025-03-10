package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends YoutubeSearcherException {

    public ResourceNotFoundException() {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Could not find any youtube resource."),
            "Could not find any song(s)."
        );
    }
    
}
