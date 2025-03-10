package com.dimetro.discordbot.game_service.lol.dto.request.match;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class SetMatchResultRequestDTO {
    
    @NotNull(message = "matchID must be provided")
    private UUID matchId;

    @NotNull(message = "botID must be provided")
    private UUID botId;

    @NotNull(message = "winningTeamId must be provided")
    private UUID winningTeamId;

}
