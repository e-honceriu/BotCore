package com.dimetro.discordbot.musicservice.playlist.service.playlist.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class PlaylistAlreadyExistsException extends PlaylistServiceException {
    
    public PlaylistAlreadyExistsException(String title, UUID botId, String guildDiscordId) {
        super(
            HttpStatus.FORBIDDEN,
            String.format("Playlist (TITLE=%s) (BOT_ID=%s) (DISCORD_ID=%s) already exists!", title, botId, guildDiscordId),
            String.format("Playlist with title `%s` already exists in this server!", title)
        );
    }
    
}
