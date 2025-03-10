package com.dimetro.discordbot.musicservice.song.dto.request.reaction;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.entity.ReactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReactionRequestDTO {
    
    @NotNull(message = "Song ID must not be null")
    private UUID songId;

    @NotNull(message = "Bot ID must not be null")
    private UUID botId;

    @NotBlank(message = "Guild Discord ID must not be null or empty")
    private String guildDiscordId;

    @NotBlank(message = "User Discord ID must not be null or empty")
    private String userDiscordId;

    @NotNull(message = "Type must not be null")
    private ReactionType type;

}
