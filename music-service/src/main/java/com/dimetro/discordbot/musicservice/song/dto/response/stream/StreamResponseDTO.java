package com.dimetro.discordbot.musicservice.song.dto.response.stream;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.entity.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreamResponseDTO {
    
    private UUID id;
    private UUID songId;
    private String requesterDiscordId;
    private String guildDiscordId;
    private LocalDateTime requestedAt;

    public static StreamResponseDTO build(Stream stream) {
        return StreamResponseDTO.builder()
                                    .id(stream.getId())
                                    .songId(stream.getSong().getId())
                                    .requesterDiscordId(stream.getRequesterDiscordId())
                                    .guildDiscordId(stream.getGuildDiscordId())
                                    .requestedAt(stream.getRequestedAt())
                                    .build();
    } 

}