package com.dimetro.discordbot.musicservice.playlist.dto.request.song;

import java.util.List;
import java.util.UUID;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSongsToPlaylistByTitleRequestDTO {
    
    @NotNull(message = "playlistId must be provided")
    private UUID playlistId;

    @NotNull(message = "botId must be provided")
    private UUID botId;

    @NotNull(message = "requesterDiscordId must be provided")
    private String requesterDiscordId;
    
    @NotNull(message = "songTitles must be provided")
    private List<String> songTitles;
    
    private SongPlatform platform;
}
