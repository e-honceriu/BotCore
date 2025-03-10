package com.dimetro.discordbot.musicservice.song.dto.response.reaction;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.entity.Reaction;
import com.dimetro.discordbot.musicservice.song.entity.ReactionType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionResponseDTO {
    
    private UUID songId;
    private String guildDiscordId;
    private String userDiscordId;
    private ReactionType type;

    public static ReactionResponseDTO build(Reaction reaction) {
        return ReactionResponseDTO.builder()
                                .songId(reaction.getSong().getId())
                                .guildDiscordId(reaction.getGuildDiscordId())
                                .userDiscordId(reaction.getUserDiscordId())
                                .type(reaction.getType())
                                .build();
    }

}
