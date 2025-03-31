package com.dimetro.discordbot.game_service.lol.dto.request.match;

import lombok.*;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class GetLiveClientMatchRequestDTO {

    @NotNull(message = "teamOrder must be provided")
    private List<String> teamOrder;

    @NotNull(message = "teamChaos must be provided")
    private List<String> teamChaos;

    @NotNull(message = "botID must be provided")
    private UUID botId;

}
