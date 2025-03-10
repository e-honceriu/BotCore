package com.dimetro.discordbot.musicservice.playlist.dto.response.playlistsong;

import com.dimetro.discordbot.musicservice.playlist.entity.PlaylistSong;
import com.dimetro.discordbot.musicservice.song.dto.response.song.SongMetadataResponseDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistSongResponseDTO {
    
    private SongMetadataResponseDTO song;
    private int position;

    public static PlaylistSongResponseDTO build(PlaylistSong playlistSong) {
        return PlaylistSongResponseDTO.builder()
                    .song(SongMetadataResponseDTO.build(playlistSong.getSong()))
                    .position(playlistSong.getPosition())
                    .build();
    }

}
