package com.dimetro.discordbot.game_service.lol.dto.response.match;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.dto.response.team.TeamResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResultResponseDTO {
    
    private UUID matchId;
    private TeamResponseDTO winningTeam;
    private TeamResponseDTO losingTeam;

    public static MatchResultResponseDTO build(MatchResult matchResult) {
        return MatchResultResponseDTO.builder()
                                .matchId(matchResult.getMatch().getId())
                                .winningTeam(TeamResponseDTO.build(matchResult.getWinningTeam()))
                                .losingTeam(TeamResponseDTO.build(matchResult.getLosingTeam()))
                                .build();
    }

}
