package com.dimetro.discordbot.game_service.lol.dto.response.series;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.series.RankingType;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeriesResponseDTO {

    private UUID id;
    private GameType type;
    private RankingType rankingType;

    public static SeriesResponseDTO build(Series series) {
        return SeriesResponseDTO.builder()
                    .id(series.getId())
                    .type(series.getGameType())
                    .rankingType(series.getRankingType())
                    .build();
    }

}
