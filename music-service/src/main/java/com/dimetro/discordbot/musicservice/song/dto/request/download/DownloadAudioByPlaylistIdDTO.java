package com.dimetro.discordbot.musicservice.song.dto.request.download;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadAudioByPlaylistIdDTO {
    
    @NotBlank(message = "playlistId must be provided")
    private String playlistId;
    @NotNull(message = "platform must be provided")
    private SongPlatform platform;

}
