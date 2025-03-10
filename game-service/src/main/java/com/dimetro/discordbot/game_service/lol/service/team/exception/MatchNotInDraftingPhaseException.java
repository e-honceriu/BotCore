package com.dimetro.discordbot.game_service.lol.service.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.service.match.exception.MatchServiceException;

public class MatchNotInDraftingPhaseException extends MatchServiceException {

    public MatchNotInDraftingPhaseException(Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            "The match is not in the drafting phase.",
            String.format("Match with ID=%s is not in the drafting phase.", match.getId())
        );
    }
    
}
