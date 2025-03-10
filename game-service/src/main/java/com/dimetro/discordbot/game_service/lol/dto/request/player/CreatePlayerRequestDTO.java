package com.dimetro.discordbot.game_service.lol.dto.request.player;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequestDTO {
    
    @NotBlank(message = "discordId must not be null or empty")
    private String discordId;

    private String riotId;
    
    @NotNull(message = "botId must be provided")
    private UUID botId;

    public Player toPlayer() {
        return Player.builder()
                .discordId(getDiscordId())
                .riotId(getRiotId())
                .botId(botId)
                .build();
    }
}
