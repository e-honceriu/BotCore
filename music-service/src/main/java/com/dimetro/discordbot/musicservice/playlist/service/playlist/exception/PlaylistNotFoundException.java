package com.dimetro.discordbot.musicservice.playlist.service.playlist.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class PlaylistNotFoundException extends PlaylistServiceException {
    
    public PlaylistNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Playlist (ID=%s) not found.", id),
            "Playlist not found!" 
        );
    }

    public PlaylistNotFoundException(String title, UUID botId, String guildDiscordId) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Playlist (TITLE=%s) (BOT_ID=%s) (DISCORD_ID=%s) not found.", title, botId, guildDiscordId),
            String.format("Playlist with title `%s` not found in this server!", title) 
        );
    }

}
