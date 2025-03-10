package com.dimetro.discordbot.musicservice.song.dto.response.externalid;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.entity.ExternalId;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalIdResponseDTO {
    
    private SongPlatform platform;
    private String externalId;

    public static ExternalIdResponseDTO build(ExternalId externalId) {
        return ExternalIdResponseDTO.builder()
                    .platform(externalId.getPlatform())
                    .externalId(externalId.getExternalId())
                    .build();
    }

}
