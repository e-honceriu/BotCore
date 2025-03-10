package com.dimetro.discordbot.game_service.lol.dto.request.match;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class CreateMatchRequestDTO {
    
    @NotNull(message = "seriesId must be provided")
    private UUID seriesId;

    @NotNull(message = "botId must be probided")
    private UUID botId;

}
