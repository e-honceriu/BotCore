package com.dimetro.discordbot.game_service.lol.service.team.exception;

import org.springframework.http.HttpStatus;

public class TeamChampionPoolNotProvidedException extends TeamServiceException {
    
    public TeamChampionPoolNotProvidedException() { 
        super(
            HttpStatus.BAD_REQUEST,
            "No team provided for the champion pool.",
            "Champion pool's team value is null."
        );
    }

}
