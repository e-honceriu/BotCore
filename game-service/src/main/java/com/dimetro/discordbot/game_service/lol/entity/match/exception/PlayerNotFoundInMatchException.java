package com.dimetro.discordbot.game_service.lol.entity.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class PlayerNotFoundInMatchException extends MatchException {
    
    public PlayerNotFoundInMatchException(Player player, Match match) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Player <@%s> not found in the match with ID=%s!", player.getDiscordId(), match.getId()),
            String.format("Player (ID=%s) not found in match (ID=%s)", player.getId(), match.getId())
        );
    }

}
