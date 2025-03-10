package com.dimetro.discordbot.musicservice.playlist.dto.response.playlist;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dimetro.discordbot.musicservice.playlist.dto.response.playlistsong.PlaylistSongResponseDTO;
import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;
import com.dimetro.discordbot.musicservice.playlist.entity.PlaylistSong;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistResponseDTO {
    
    private UUID playlistId;
    private String title;
    private String ownerDiscordId;
    private String guildDiscordId;
    private List<PlaylistSongResponseDTO> songs;

    public static PlaylistResponseDTO build(Playlist playlist) {
        return PlaylistResponseDTO.builder()
                .playlistId(playlist.getId())
                .title(playlist.getTitle())
                .ownerDiscordId(playlist.getOwnerDiscordId())
                .guildDiscordId(playlist.getGuildDiscordId())
                .songs(
                    playlist.getSongs().stream()
                        .sorted(
                            Comparator.comparingInt(PlaylistSong::getPosition)
                        )
                        .map(
                            pSong -> PlaylistSongResponseDTO.build(pSong)
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

}
