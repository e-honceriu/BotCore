package com.dimetro.discordbot.game_service.lol.dto.request.series;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.series.RankingType;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSeriesRequestDTO {
    
    @NotNull(message = "Type must be provided")
    private GameType type;

    @NotNull(message = "Ranking Type must be provided")
    private RankingType rankingType;

    @NotNull(message = "Bot ID must be provided")
    private UUID botId;
    
    @NotBlank(message = "Guild Discord ID must be provided and it must not be empty")
    private String guildDiscordId;

    public Series toSeries() {
        return Series.builder()
                .gameType(type)
                .rankingType(rankingType)
                .botId(botId)
                .guildDiscordId(guildDiscordId)
                .build();
    }

}
