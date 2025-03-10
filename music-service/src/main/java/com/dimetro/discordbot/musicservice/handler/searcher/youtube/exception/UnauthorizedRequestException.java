package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedRequestException extends YoutubeSearcherException {

    public UnauthorizedRequestException() {
        super(
            HttpStatus.UNAUTHORIZED,
            String.format("Unauthorized youtube API call."),
            "Could not search for song(s)."
        );
    }
    
}
