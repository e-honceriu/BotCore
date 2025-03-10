package com.dimetro.discordbot.musicservice.playlist.dto.request.playlist;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistRequestDTO {
    
    @NotBlank(message = "title must be provided and not be empty")
    private String title;

    @NotBlank(message = "ownerDiscordId must be provided and not be empty")
    private String ownerDiscordId;

    @NotNull(message = "botId must be provided")
    private UUID botId;

    @NotBlank(message = "guildDiscordId must be provided and not be empty")
    private String guildDiscordId;

}
