package com.dimetro.discordbot.musicservice.song.dto.response.download;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.dto.response.song.SongMetadataResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Download;
import com.dimetro.discordbot.musicservice.song.entity.DownloadStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadResponseDTO {
    
    private UUID id;
    private DownloadStatus status;
    private SongMetadataResponseDTO song;

    public static DownloadResponseDTO build(Download download) {
        return DownloadResponseDTO.builder()
                            .id(download.getId())
                            .status(download.getStatus())
                            .song(SongMetadataResponseDTO.build(download.getSong()))
                            .build();
    }

}
