package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class MatchResultAlreadyExistsException extends MatchServiceException {
    
    public MatchResultAlreadyExistsException(Match match) {
        super(
            HttpStatus.CONFLICT,
            "The result for this match has already been recorded.",
            String.format("Match (ID=%s) already has a result set.", match.getId())
        );
    }

}
