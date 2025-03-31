package com.dimetro.discordbot.musicservice.playlist.service.playlist.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;

public class PlaylistDoesNotBelongToBotException extends PlaylistServiceException {

    public PlaylistDoesNotBelongToBotException(Playlist playlist) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Playlist (ID=%s), attempted to be managed by a bot it does not belong to.", playlist.getId()),
            String.format("Playlist does not belong to the bot.", playlist.getId())
        );
    }
    
}
