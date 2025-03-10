package com.dimetro.discordbot.musicservice.song.dto.response.song;

import lombok.*;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.dto.response.externalid.ExternalIdResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Song;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongMetadataResponseDTO {
    
    private UUID id;
    private String title;
    private String thumbnailUrl;
    private boolean audioFileAvailable;
    private ExternalIdResponseDTO externalId;

    public static SongMetadataResponseDTO build(Song song) {
        return SongMetadataResponseDTO.builder()
                        .id(song.getId())
                        .title(song.getTitle())
                        .thumbnailUrl(song.getThumbnailUrl())
                        .audioFileAvailable(song.getAudioFilePath() != null)
                        .externalId(ExternalIdResponseDTO.build(song.getExternalId()))
                        .build();
    }

}
