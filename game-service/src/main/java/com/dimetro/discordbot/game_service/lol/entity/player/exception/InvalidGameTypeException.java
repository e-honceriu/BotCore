package com.dimetro.discordbot.game_service.lol.entity.player.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

public class InvalidGameTypeException extends PlayerException {
    
        public InvalidGameTypeException(GameType gameType) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Unknown Elo type: %s provided.", gameType.getLabel()),
            String.format("The provided gameType='%s' is not valid or supported.", gameType.getLabel())
        );
    }

}
