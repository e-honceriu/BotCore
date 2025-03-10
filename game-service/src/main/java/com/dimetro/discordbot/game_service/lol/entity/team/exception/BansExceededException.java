package com.dimetro.discordbot.game_service.lol.entity.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class BansExceededException extends TeamException {
    
    public BansExceededException(Team team, int maxBans) {
        super(
            HttpStatus.UNPROCESSABLE_ENTITY,
            String.format("Your team already has %s ban(s)!", maxBans),
            String.format("Team (ID=%s) already has %s ban(s)!", team.getId(), maxBans)
        );
    }

}
