package com.dimetro.discordbot.musicservice.playlist.service.playlist.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class SongNotFoundInPlaylistException extends PlaylistServiceException {
    
    public SongNotFoundInPlaylistException(UUID playlistId, UUID songId, Integer position) {
        super(
            HttpStatus.NOT_FOUND,
            getDevMessage(playlistId, songId, position),
            getUserMessage(position)
        );
    }

    private static String getDevMessage(UUID playlistId, UUID songId, Integer position) {

        if (songId != null) {
            return String.format("Song (ID=%s) not found in playlist (ID=%s).", songId, playlistId);
        }

        if (position != null) {
            return String.format("Song at position #%d not found in playlist (ID=%s).", position, playlistId);
        }

        return "Song not found in playlist.";
    }

    private static String getUserMessage(Integer position) {

        if (position != null) {
            return String.format("No song found at position #%d in the playlist!", position);
        }
        
        return "Song not found in playlist.";
    }

}
