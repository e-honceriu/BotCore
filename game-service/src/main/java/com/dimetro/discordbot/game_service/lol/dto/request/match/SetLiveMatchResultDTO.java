package com.dimetro.discordbot.game_service.lol.dto.request.match;

import java.util.UUID;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetLiveMatchResultDTO {
    
    @NotNull(message = "matchId must be provided")
    private UUID matchId;

    @NotNull(message = "botId must be provided")
    private UUID botId;

    @NotBlank(message = "rioId must be provided and it must not be null")
    private String riotId;

    @NotNull(message = "result must provided")
    private RequestMatchResult result;

}
