package com.dimetro.discordbot.game_service.lol.dto.response.team;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.dto.response.champion.ChampionResponseDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.player.PlayerResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponseDTO {
    
    private UUID id;
    private List<PlayerResponseDTO> players;
    private Set<ChampionResponseDTO> bans;

    public static TeamResponseDTO build(Team team) {
        return TeamResponseDTO.builder()
                .id(team.getId())
                .players(PlayerResponseDTO.build(team.getPlayers()))
                .bans(ChampionResponseDTO.build(team.getBans()))
                .build();
    }

    public static List<TeamResponseDTO> build(List<Team> teams) {
        return teams.stream().map(TeamResponseDTO::build).collect(Collectors.toList());
    }

}
