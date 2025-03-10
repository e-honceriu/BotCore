package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class WinningTeamNotFoundException extends MatchServiceException {
    
    public WinningTeamNotFoundException(Player player, Match match) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Winning team not found for player <@%s> in the provided match!", player.getDiscordId()),
            String.format("Could not determine the winning team for Player (ID=%s) in Match (ID=%s).", player.getId(), match.getId())
        );
    }

}
