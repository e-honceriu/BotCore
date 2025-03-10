package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class MatchNotInBanningPhaseException extends MatchServiceException{
    
    public MatchNotInBanningPhaseException(Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            "The match's banning phase already ended!",
            String.format("Match with id=%s not in TEAM_GENERATION or DRAFTING stage, current status=%s.", 
                          match.getId(), match.getStatus())
        );
    }

}
