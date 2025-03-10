package com.dimetro.discordbot.game_service.lol.entity.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class PlayerFoundInAllTeamsException extends MatchException{

    public PlayerFoundInAllTeamsException(Player player, Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Player <@%s> is part of both teams, unable to determine opposing team.", player.getDiscordId()),
            String.format("Player (ID=%s) is present in both teams in Match (ID=%s).", player.getId(), match.getId())
        );
    }
    
}

