package com.dimetro.discordbot.musicservice.playlist.dto.request.song;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator.RemoveSongFromPlaylistRequestDTOConstraint;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RemoveSongFromPlaylistRequestDTOConstraint
public class RemoveSongFromPlaylistRequestDTO {
    
    private UUID playlistId;
    private UUID botId;
    private String requesterDiscordId;
    private UUID songId;
    private Integer position;

}
