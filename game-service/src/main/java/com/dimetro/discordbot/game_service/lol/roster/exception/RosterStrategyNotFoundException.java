package com.dimetro.discordbot.game_service.lol.roster.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

public class RosterStrategyNotFoundException extends RosterException {
    
    public RosterStrategyNotFoundException(GameType gameType) {
        super(
            HttpStatus.UNPROCESSABLE_ENTITY, 
            "Could not compute roster strategy for match type: " + gameType.getLabel() + ".", 
            "Roster strategy for game type '" + gameType.getLabel() + "' could not be found in the system."
        );
    }

}
