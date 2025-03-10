package com.dimetro.discordbot.game_service.lol.draft.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

public class DraftStrategyNotFoundException extends DraftException {
    
    public DraftStrategyNotFoundException(GameType gameType) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Could not create the draft for match type: " + gameType.getLabel() + ".", 
            "Draft strategy for game type '" + gameType.getLabel() + "' could not be found in the system."
        );
    }

}
