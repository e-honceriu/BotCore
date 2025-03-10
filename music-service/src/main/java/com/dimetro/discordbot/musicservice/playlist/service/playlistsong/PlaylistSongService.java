package com.dimetro.discordbot.musicservice.playlist.service.playlistsong;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.playlist.entity.PlaylistSong;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;

@Service
public class PlaylistSongService {
    
    private final SongService songService;

    @Autowired
    public PlaylistSongService(SongService songService) {
        this.songService = songService;
    }

    public PlaylistSong createBarebonePlaylistSongBySongId(UUID songId) {
    
        PlaylistSong playlistSong = new PlaylistSong();

        playlistSong.setSong(songService.getSongByIdOrThrow(songId));

        return playlistSong;
    }

    public PlaylistSong createBareBonePlaylistSongByTitle(SongPlatform platform, String title) {

        PlaylistSong playlistSong = new PlaylistSong();

        playlistSong.setSong(songService.searchSongByPlatformAndTitle(platform, title));

        return playlistSong;
    }

    public PlaylistSong createBareBonePlaylistSongByExternalId(SongPlatform platform, String externalId) {

        PlaylistSong playlistSong = new PlaylistSong();

        playlistSong.setSong(songService.searchSongByPlatformAndExternalId(platform, externalId));

        return playlistSong;
    }

}
