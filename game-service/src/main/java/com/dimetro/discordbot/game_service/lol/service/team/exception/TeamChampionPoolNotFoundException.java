package com.dimetro.discordbot.game_service.lol.service.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class TeamChampionPoolNotFoundException extends TeamServiceException {
    
    public TeamChampionPoolNotFoundException(Team team) { 
        super(
            HttpStatus.NOT_FOUND,
            "No champion pool found.",
            String.format("No champion pool found for Team (ID=%s).", team.getId())
        );
    }

}
