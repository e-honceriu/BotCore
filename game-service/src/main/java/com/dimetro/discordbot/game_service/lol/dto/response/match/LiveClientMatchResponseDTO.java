package com.dimetro.discordbot.game_service.lol.dto.response.match;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveClientMatchResponseDTO {
    
    private UUID matchId;

    public static LiveClientMatchResponseDTO build(Match match) {
        return LiveClientMatchResponseDTO.builder()
                                    .matchId(match.getId())
                                    .build();
    }

}
