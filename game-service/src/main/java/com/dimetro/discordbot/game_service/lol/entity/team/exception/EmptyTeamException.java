package com.dimetro.discordbot.game_service.lol.entity.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class EmptyTeamException extends TeamException {
    
    public EmptyTeamException(Team team) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Team with ID=%s cannot be empty.", team.getId()),
            String.format("Attempted to perform an operation with an empty Team (ID=%s).", team.getId())
        );
    }

}
