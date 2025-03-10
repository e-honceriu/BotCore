package com.dimetro.discordbot.musicservice.song.dto.response.reaction;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.entity.Song;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongReactionResponseDTO {
    
    private UUID songId;
    private long likes;
    private long dislikes;

    public static SongReactionResponseDTO build(Song song, long likes, long dislikes) {
        return SongReactionResponseDTO.builder()
                                .songId(song.getId())
                                .likes(likes)
                                .dislikes(dislikes)
                                .build();
    }

}
