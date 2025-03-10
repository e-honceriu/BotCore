package com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception;

import org.springframework.http.HttpStatus;

public class InvalidAPIResponseException extends YoutubeSearcherException {

    public InvalidAPIResponseException(Throwable e) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Invalid YouTube API response! Cause: " + e.getMessage(),
            "Could not search for song(s).",
            e
        );
    }

    public InvalidAPIResponseException(String field) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Field %s from YouTube API response is missing or has wrong values!", field),
            "Could not search for song(s)."
        );
    }

    public InvalidAPIResponseException(Class<?> expectedType, Object received) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            String.format(
                "Invalid YouTube API response. Expected response of type: %s, but received: %s - %s",
                expectedType.getName(), received.getClass().getName(), received.toString()
            ),
            "Could not search for song(s)."
        );
    }

}

