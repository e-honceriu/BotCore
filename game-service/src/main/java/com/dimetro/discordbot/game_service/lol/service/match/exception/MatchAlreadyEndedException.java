package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class MatchAlreadyEndedException extends MatchServiceException {
    
    public MatchAlreadyEndedException(Match match) {
        super(
            HttpStatus.CONFLICT,
            "The match has already ended.",
            String.format("Match (ID=%s) has already been concluded.", match.getId())
        );
    }

}
