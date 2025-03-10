package com.dimetro.discordbot.game_service.lol.service.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class TeamDoesNotBelongToBotException extends TeamServiceException {

    public TeamDoesNotBelongToBotException(Team match) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Team does not belong to the bot.", match.getId()),
            String.format("Team (ID=%s), attempted to be managed by a bot it does not belong to.", match.getId())
        );
    }
    
}
