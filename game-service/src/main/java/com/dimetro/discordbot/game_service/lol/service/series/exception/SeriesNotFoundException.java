package com.dimetro.discordbot.game_service.lol.service.series.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class SeriesNotFoundException extends SeriesServiceException {
    
    public SeriesNotFoundException(UUID seriesId) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Series not found!", seriesId),
            String.format("No series found with the ID=%s.", seriesId)
        );
    }

}
