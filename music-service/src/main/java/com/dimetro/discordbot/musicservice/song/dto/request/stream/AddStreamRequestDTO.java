package com.dimetro.discordbot.musicservice.song.dto.request.stream;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStreamRequestDTO {

    @NotNull(message = "Song ID must be provided")
    private UUID songId;

    @NotNull(message = "Bot ID must be provided")
    private UUID botId;

    @NotBlank(message = "Guild Discord ID must be provided and it must not be empty")
    private String guildDiscordId;

    @NotBlank(message = "Requester Discord ID must be provided and it must not be empty")
    private String requesterDiscordId;

    @NotNull(message = "Requester At must be provided")
    private LocalDateTime requestedAt;

}
