package com.dimetro.discordbot.game_service.lol.service.player.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class PlayerStatsNotFoundException extends PlayerServiceException {
    
    public PlayerStatsNotFoundException(Player player, Match match) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Stats for player <@%s> not found!", player.getDiscordId()),
            String.format("Player stats not found for Player (ID=%s) Match (ID=%s).", player.getId(), match.getId())
        );
    }

}   
