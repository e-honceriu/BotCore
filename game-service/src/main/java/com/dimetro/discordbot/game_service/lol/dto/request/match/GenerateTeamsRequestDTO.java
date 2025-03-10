package com.dimetro.discordbot.game_service.lol.dto.request.match;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateTeamsRequestDTO {
    
    @NotNull(message = "matchID must be provided")
    private UUID matchId;

    @NotNull(message = "botID must be provided")
    private UUID botId;

    @NotNull(message = "playerIds must be provided")
    private List<UUID> playerIds;

}
