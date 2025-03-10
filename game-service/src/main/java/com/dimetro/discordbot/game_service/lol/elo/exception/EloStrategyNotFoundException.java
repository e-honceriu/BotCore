package com.dimetro.discordbot.game_service.lol.elo.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.series.RankingType;

public class EloStrategyNotFoundException extends EloException {
    
    public EloStrategyNotFoundException(RankingType rankingType) {
        super(
            HttpStatus.UNPROCESSABLE_ENTITY, 
            "Could not compute Elo for match type: " + rankingType.getLabel() + ".", 
            "Elo strategy for ranking type '" + rankingType.getLabel() + "' could not be found in the system."
        );
    }

}
