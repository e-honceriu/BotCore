package com.dimetro.discordbot.game_service.lol.entity.player.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

public class PlayerEloNotFoundException extends PlayerException {
    
    public PlayerEloNotFoundException(GameType gameType, Player player) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("%s Elo not found for player <@%s>", gameType.getLabel(), player.getDiscordId()),
            String.format("Elo value not found for gameType='%s' and player with ID=%d", gameType.getLabel(), player.getId())
        );
    }

    public PlayerEloNotFoundException(Player player) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Elo not found for player <@%s>", player.getDiscordId()),
            String.format("Elo not found for player with ID=%d", player.getId())
        );
    }

}
