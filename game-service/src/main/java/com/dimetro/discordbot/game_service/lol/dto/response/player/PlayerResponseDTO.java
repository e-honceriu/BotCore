package com.dimetro.discordbot.game_service.lol.dto.response.player;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponseDTO {
    
    private UUID id;
    private String discordId;
    private String riotId;
    private Map<GameType, Integer> elo;

    public static PlayerResponseDTO build(Player player) {
        return PlayerResponseDTO.builder()
                .id(player.getId())
                .discordId(player.getDiscordId())
                .riotId(player.getRiotId())
                .elo(player.getPlayerElo().getEloMap())
                .build();
    }

    public static List<PlayerResponseDTO> build(List<Player> players) {
        return players.stream().map(PlayerResponseDTO::build).collect(Collectors.toList());
    }

}
