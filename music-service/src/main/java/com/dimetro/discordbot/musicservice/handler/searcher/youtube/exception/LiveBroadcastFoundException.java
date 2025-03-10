package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class LiveBroadcastFoundException extends YoutubeSearcherException {
    
    public LiveBroadcastFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "Requested song is a livestream!",
            "The requested song is a livestream!"
        );
    }

}
