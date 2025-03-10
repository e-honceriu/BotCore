package com.dimetro.discordbot.game_service.lol.service.series.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.series.Series;

public class SeriesDoesNotBelongToBotException extends SeriesServiceException {
    
    public SeriesDoesNotBelongToBotException(Series player) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Player does not belong to the bot.", player.getId()),
            String.format("Player (ID=%s), attempted to be managed by a bot it does not belong to.", player.getId())
        );
    }

}
