package com.dimetro.discordbot.game_service.lol.dto.request.team;

import java.util.UUID;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTeamChampPoolRequestDTO {
    
    @NotNull(message = "teamId must be provided")
    private UUID teamId;

    @NotNull(message = "botId must be provided")
    private UUID botId;

}
