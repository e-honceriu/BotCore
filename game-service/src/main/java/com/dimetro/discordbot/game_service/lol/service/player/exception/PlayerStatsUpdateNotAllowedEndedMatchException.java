package com.dimetro.discordbot.game_service.lol.service.player.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class PlayerStatsUpdateNotAllowedEndedMatchException extends PlayerServiceException {
    
    public PlayerStatsUpdateNotAllowedEndedMatchException(Match match) {
        super(
            HttpStatus.CONFLICT,
            "Cannot update player stats for a completed match.",
            String.format("Attempted to update stats for Match (ID=%s), but the match has already ended.", match.getId())
        );
    }

}
