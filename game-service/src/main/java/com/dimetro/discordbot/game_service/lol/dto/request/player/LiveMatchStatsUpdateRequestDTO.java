package com.dimetro.discordbot.game_service.lol.dto.request.player;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveMatchStatsUpdateRequestDTO {
    
    @NotNull (message = "matchId must be provided")
    private UUID matchId;

    @NotNull (message = "botId must be provided")
    private UUID botId;

    @NotNull (message = "playerStats must be provided")
    private List<LivePlayerStatsUpdateRequestDTO> playerStats;
}
