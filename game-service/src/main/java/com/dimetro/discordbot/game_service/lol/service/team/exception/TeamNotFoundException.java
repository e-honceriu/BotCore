package com.dimetro.discordbot.game_service.lol.service.team.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends TeamServiceException {
    
    public TeamNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            "Team not found!",
            String.format("Team with ID=%s not found!", id)
        );
    }

}
