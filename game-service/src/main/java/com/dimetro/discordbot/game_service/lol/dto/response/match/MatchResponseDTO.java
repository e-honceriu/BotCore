package com.dimetro.discordbot.game_service.lol.dto.response.match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.dto.response.series.SeriesResponseDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.team.TeamResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResponseDTO {
    
    private UUID id;
    private SeriesResponseDTO series;
    private MatchStatus status;
    private LocalDateTime startTime;
    private List<TeamResponseDTO> teams;

    public static MatchResponseDTO build(Match match) {
        return MatchResponseDTO.builder()
                        .id(match.getId())
                        .series(SeriesResponseDTO.build(match.getSeries()))
                        .status(match.getStatus())
                        .startTime(match.getStartTime())
                        .teams(TeamResponseDTO.build(match.getTeams()))
                        .build();
    }

}
