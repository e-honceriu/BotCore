package com.dimetro.discordbot.game_service.lol.service.player.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends PlayerServiceException {
    
    public PlayerNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            "Player not found!",
            String.format("Player with ID=%s not found!", id)
        );
    }

    public PlayerNotFoundException(String discordId, String riotId) {
        super(
            HttpStatus.NOT_FOUND,
            buildUserMessage(discordId, riotId),
            buildDeveloperMessage(discordId, riotId)
        );
    }

    private static String buildUserMessage(String discordId, String riotId) {
        if (discordId != null && riotId != null) {
            return String.format("Player <@%s> (Riot ID: %s) not found!", discordId, riotId);
        }
        if (discordId != null) {
            return String.format("Player <@%s> not found!", discordId);
        }
        if (riotId != null) {
            return String.format("Player with Riot ID=%s not found!", riotId);
        }
        return "Player not found!";
    }

    private static String buildDeveloperMessage(String discordId, String riotId) {
        if (discordId != null && riotId != null) {
            return String.format("Player (Discord ID=%s Riot ID=%s) not found!", discordId, riotId);
        }
        if (discordId != null) {
            return String.format("Player (Discord ID=%s) not found!", discordId);
        }
        if (riotId != null) {
            return String.format("Player (Riot ID=%s) not found!", riotId);
        }
        return "Player not found!";
    }

}
