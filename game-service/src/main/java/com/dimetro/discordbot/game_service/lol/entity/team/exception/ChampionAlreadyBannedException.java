package com.dimetro.discordbot.game_service.lol.entity.team.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class ChampionAlreadyBannedException extends TeamException {
    
    public ChampionAlreadyBannedException(Team team, Champion champion) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Your team already banned %s!", champion.getName()),
            String.format("Team (ID=%s) already banned Champion (ID=%s).", team.getId(), champion.getId())
        );
    }

}
