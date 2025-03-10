package com.dimetro.discordbot.musicservice.song.dto.response.engagement;

import java.util.UUID;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongEngagementResponseDTO {
    
    private UUID songId;
    private long listenersCount;
    private long streamsCount;
    private long likesCount;
    private long dislikesCount;
    
}
