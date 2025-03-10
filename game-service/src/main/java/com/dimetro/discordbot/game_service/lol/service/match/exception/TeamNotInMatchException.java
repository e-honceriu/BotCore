package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class TeamNotInMatchException extends MatchServiceException {

    public TeamNotInMatchException(Team team, Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            "The selected team is not part of this match.",
            String.format("Team (ID=%s) is not associated with Match (ID=%s).", team.getId(), match.getId())
        );
    }

}
