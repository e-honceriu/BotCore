package com.dimetro.discordbot.game_service.lol.dto.request.player;

import java.util.Map;
import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPlayerRequestDTO {
    
    @NotNull(message = "playerId must not be provided")
    private UUID playerId;

    @NotNull(message = "botId must be provided")
    private UUID botId;

    private String discordId;

    private String riotId;
    
    private Map<GameType, Integer> elo;

}
