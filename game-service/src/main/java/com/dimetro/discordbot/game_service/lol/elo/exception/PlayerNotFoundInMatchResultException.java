package com.dimetro.discordbot.game_service.lol.elo.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class PlayerNotFoundInMatchResultException extends EloException{
    
    public PlayerNotFoundInMatchResultException(Player player, Match match) {
        super(
            HttpStatus.NOT_FOUND,
            "Player <@" + player.getDiscordId() + "> not found in the match!",
            "Player with ID=" + player.getId() + " not found in the match with ID=" + match.getId() + "."
        );
    }

}
