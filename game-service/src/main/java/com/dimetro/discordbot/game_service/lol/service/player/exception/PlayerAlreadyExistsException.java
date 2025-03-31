package com.dimetro.discordbot.game_service.lol.service.player.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class PlayerAlreadyExistsException extends PlayerServiceException {
    
    public PlayerAlreadyExistsException(Player player) {
        super(
            HttpStatus.CONFLICT,
            buildUserMessage(player),
            buildDeveloperMessage(player)
        );
    }

    private static String buildUserMessage(Player player) {
        if (player.getRiotId() != null) {
            return String.format("Player <@%s> (Riot ID: %s) already exists!", player.getDiscordId(), player.getRiotId());
        }
        return String.format("Player (D<@%s> already exists!", player.getDiscordId());
    }

    private static String buildDeveloperMessage(Player player) {
        if (player.getRiotId() != null) {
            return String.format("Player (Discord ID=%s Riot ID=%s) already exists.", player.getDiscordId(), player.getRiotId());
        }
        return String.format("Player (Discord ID=%s) already exists.", player.getDiscordId());
    }
    
}
