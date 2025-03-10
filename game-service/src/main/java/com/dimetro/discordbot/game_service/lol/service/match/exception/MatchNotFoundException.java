package com.dimetro.discordbot.game_service.lol.service.match.exception;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public class MatchNotFoundException extends MatchServiceException {
    
    public MatchNotFoundException(List<Player> players) {
        super(
            HttpStatus.NOT_FOUND,
            buildUserMessage(players),
            String.format("No match found containing players: %s", players)
        );
    }

    public MatchNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            "Match not found.",
            String.format("No match found with ID=%s", id)
        );
    }

    private static String buildUserMessage(List<Player> players) {
        StringBuilder message = new StringBuilder("No match found containing the following players: [");
        for (Player player : players) {
            message.append("<@").append(player.getDiscordId()).append("> ");
        }
        message.append("]");
        return message.toString();
    }

}
