package com.dimetro.discordbot.musicservice.song.service.song;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.dto.response.song.SongMetadataResponseDTO;

@Service
public class SongMetadataService {
    
    private final SongService songService;

    @Autowired
    public SongMetadataService(SongService songService) {
        this.songService = songService;
    }

    public SongMetadataResponseDTO getSongByExternalId(SongPlatform platform, String id) {

        Song song = songService.searchSongByPlatformAndExternalId(platform, id);

        return SongMetadataResponseDTO.build(song);
    }

    public SongMetadataResponseDTO getSongById(UUID id) {

        Song song = songService.getSongByIdOrThrow(id);

        return SongMetadataResponseDTO.build(song);
    }

    public SongMetadataResponseDTO getSongByTitle(SongPlatform platform, String title) {

        Song song = songService.searchSongByPlatformAndTitle(platform, title);

        return SongMetadataResponseDTO.build(song);
    }

    public List<SongMetadataResponseDTO> getSongsByPlaylistId(SongPlatform platform, String playlistId) {

        List<Song> songs = songService.searchSongsByExternalPlaylistId(platform, playlistId);

        return songs.stream()
                .map(SongMetadataResponseDTO::build)
                .collect(Collectors.toList());
    }

    public List<SongMetadataResponseDTO> getSongsByAlbumId(SongPlatform platform, String albumId) {

        List<Song> songs = songService.searchSongsByAlbumId(platform, albumId);
        
        return songs.stream()
                .map(SongMetadataResponseDTO::build)
                .collect(Collectors.toList());
    }

}
