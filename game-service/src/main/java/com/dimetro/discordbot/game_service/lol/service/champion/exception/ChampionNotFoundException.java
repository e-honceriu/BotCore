package com.dimetro.discordbot.game_service.lol.service.champion.exception;

import org.springframework.http.HttpStatus;

public class ChampionNotFoundException extends ChampionServiceException {
    
    public ChampionNotFoundException(String championName) {
        super(
            HttpStatus.NOT_FOUND, 
            String.format("Champion `%s` not found.", championName),
            String.format("Champion (name=%s) not found.", championName)
        );
    }

}
