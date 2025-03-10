package com.dimetro.discordbot.game_service.lol.dto.response.champion;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChampionResponseDTO {
    
    private UUID id;
    private String riotId;
    private String name;

    public static ChampionResponseDTO build(Champion champion) {
        return ChampionResponseDTO.builder()
                    .id(champion.getId())
                    .riotId(champion.getRiotId())
                    .name(champion.getName())
                    .build();
    }

    public static Set<ChampionResponseDTO> build(Set<Champion> champions) {
        return champions.stream().map(ChampionResponseDTO::build).collect(Collectors.toSet());
    }

    public static List<ChampionResponseDTO> build(List<Champion> champions) {
        return champions.stream().map(ChampionResponseDTO::build).collect(Collectors.toList());
    }

}
