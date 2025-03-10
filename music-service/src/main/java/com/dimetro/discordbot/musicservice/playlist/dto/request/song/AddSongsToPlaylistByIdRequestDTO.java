package com.dimetro.discordbot.musicservice.playlist.dto.request.song;

import java.util.List;
import java.util.UUID;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator.AddSongsToPlaylistByIdRequestDTOConstraint;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@AddSongsToPlaylistByIdRequestDTOConstraint
public class AddSongsToPlaylistByIdRequestDTO {
    
    private UUID playlistId;
    private UUID botId;
    private String requesterDiscordId;
    private List<UUID> songIds;
    private List<String> songExternalIds;
    private SongPlatform platform;
    
}
