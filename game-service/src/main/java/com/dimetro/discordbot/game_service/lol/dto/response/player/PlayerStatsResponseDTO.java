package com.dimetro.discordbot.game_service.lol.dto.response.player;

import com.dimetro.discordbot.game_service.lol.dto.response.champion.ChampionResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerStatsResponseDTO {
    
    private ChampionResponseDTO champion;
    private Integer eloGain;
    private Integer kills;
    private Integer assists;
    private Integer deaths;
    private Integer creepScore;

    public static PlayerStatsResponseDTO build(PlayerStats playerStats) {
        Champion playedChampion = playerStats.getPlayedChampion();
        return PlayerStatsResponseDTO.builder()
                    .champion(playedChampion != null ? ChampionResponseDTO.build(playedChampion) : null)
                    .eloGain(playerStats.getEloGain())
                    .kills(playerStats.getKills())
                    .assists(playerStats.getAssists())
                    .deaths(playerStats.getDeaths())
                    .creepScore(playerStats.getCreepScore())
                    .build();
    }

}
