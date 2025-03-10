package com.dimetro.discordbot.musicservice.song.dto.request.download;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.dto.request.download.validator.DownloadByIdDTOConstraint;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DownloadByIdDTOConstraint
public class DownloadAudioByIdRequestDTO {
    
    private UUID songId;
    private SongPlatform platform;
    private String externalId;

}
