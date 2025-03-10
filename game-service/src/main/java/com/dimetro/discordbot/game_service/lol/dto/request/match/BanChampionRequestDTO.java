package com.dimetro.discordbot.game_service.lol.dto.request.match;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class BanChampionRequestDTO {
    
    @NotNull(message = "Player ID must be provided")
    private UUID playerId;

    @NotNull(message = "Bot ID must be provided")
    private UUID botId;

    @NotNull(message = "Match ID must be provided")
    private UUID matchId;
    
    @NotBlank(message = "Champion must be provided and it must not be empty")
    private String champion;
    
}
