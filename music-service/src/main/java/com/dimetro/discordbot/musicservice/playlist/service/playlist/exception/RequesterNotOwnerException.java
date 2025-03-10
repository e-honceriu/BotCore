package com.dimetro.discordbot.musicservice.playlist.service.playlist.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;

public class RequesterNotOwnerException extends PlaylistServiceException {
    
    public RequesterNotOwnerException(Playlist playlist, String requesterId) {
        super(
            HttpStatus.FORBIDDEN,
            String.format("Discord user (ID=%s) not owner of playlist (ID=%s).", requesterId, playlist.getId()),
            "You are not the owner of the playlist!" 
        );
    }

}
