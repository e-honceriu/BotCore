package com.dimetro.discordbot.game_service.lol.entity.champion;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.exception.LolGameServiceException;

public class ChampionAlreadyBannedException extends LolGameServiceException {
    
    public ChampionAlreadyBannedException(Team team, Champion champion) {
        super(
            HttpStatus.BAD_REQUEST,
            "Your team already banned " + champion.getName() + "!",
            "Team with ID=" + team.getId() + " already banned champion with ID=" + champion.getId()
        );
    }

}
