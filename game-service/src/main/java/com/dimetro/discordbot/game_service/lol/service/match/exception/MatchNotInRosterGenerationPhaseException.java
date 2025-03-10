package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class MatchNotInRosterGenerationPhaseException extends MatchServiceException {
    
    public MatchNotInRosterGenerationPhaseException(Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            "The roster generation phase for this match has already ended.",
            String.format("Match (ID=%s) is not in the TEAM_GENERATION stage.", match.getId())
        );
    }

}
