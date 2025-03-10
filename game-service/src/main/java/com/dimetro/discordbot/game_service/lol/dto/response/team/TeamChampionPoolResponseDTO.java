package com.dimetro.discordbot.game_service.lol.dto.response.team;

import java.util.List;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.dto.response.champion.ChampionResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.team.TeamChampionPool;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamChampionPoolResponseDTO {
    
    private List<ChampionResponseDTO> champions;

    public static TeamChampionPoolResponseDTO build(TeamChampionPool championPool) {
        return TeamChampionPoolResponseDTO.builder()
                                        .champions(ChampionResponseDTO.build(championPool.getChampions()))
                                        .build();
    }

    public static List<TeamChampionPoolResponseDTO> build(List<TeamChampionPool> championPools) {
        return championPools.stream().map(TeamChampionPoolResponseDTO::build).collect(Collectors.toList());
    }

}
