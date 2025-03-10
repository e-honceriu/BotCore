package com.dimetro.discordbot.game_service.lol.dto.request.player;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivePlayerStatsUpdateRequestDTO {
    
    @NotNull(message = "riotId must be provided")
    private String riotId;

    @NotNull(message = "champion must be provided")
    private String champion;

    @NotNull(message = "kills must be provided")
    @Min(0)
    private Integer kills;

    @NotNull(message = "assists must be provided")
    @Min(0)
    private Integer assists;

    @NotNull(message = "deaths must be provided")
    @Min(0)
    private Integer deaths;

    @NotNull(message = "creepScore must be provided")
    @Min(0)
    private Integer creepScore;

}
