package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends YoutubeSearcherException {
    
    public BadRequestException() {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Failed to make request to YouTube API."),
            "Failed to search for song(s)."
        );
    }

}
