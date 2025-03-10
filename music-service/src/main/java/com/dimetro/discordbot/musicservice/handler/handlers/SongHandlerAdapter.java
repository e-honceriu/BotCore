package com.dimetro.discordbot.musicservice.handler.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.exception.UnsupportedPlatformHandlerException;

@Component
public class SongHandlerAdapter {
    
    private final YoutubeHandler youtubeHandler;
    private final SpotifyHandler spotifyHandler;

    @Autowired
    public SongHandlerAdapter(YoutubeHandler youtubeHandler, SpotifyHandler spotifyHandler) {
        this.youtubeHandler = youtubeHandler;
        this.spotifyHandler = spotifyHandler;
    }

    protected SongHandler getHandler(SongPlatform platform) {
        if (platform == SongPlatform.YOUTUBE) {
            return youtubeHandler;
        }
        if (platform == SongPlatform.SPOTIFY) {
            return spotifyHandler;
        }
        throw new UnsupportedPlatformHandlerException(platform);
    }

    public SongSearch searchSongById(SongPlatform platform, String id) {
        SongHandler handler = getHandler(platform);
        return handler.searchSongById(id);
    }

    public SongSearch searchSongByTitle(SongPlatform platform, String title) {
        SongHandler handler = getHandler(platform);
        return handler.searchSongByTitle(title);
    }
    
    public List<SongSearch> searchSongsByPlaylistId(SongPlatform platform, String playlistId) {
        SongHandler handler = getHandler(platform);
        return handler.searchSongsByPlaylistId(playlistId);
    }

    public List<SongSearch> searchSongsByAlbumId(SongPlatform platform, String albumId) {

        if (platform == SongPlatform.YOUTUBE) {
            return searchSongsByPlaylistId(platform, albumId);
        }
        if (platform == SongPlatform.SPOTIFY) {
            return spotifyHandler.searchSongsByAlbumId(albumId);
        }
        throw new UnsupportedPlatformHandlerException(platform);
    }

    public String downloadSongById(SongPlatform platform, String id) {
        SongHandler handler = getHandler(platform);
        return handler.downloadSongById(id);
    }

    public String downloadSongByTitle(SongPlatform platform, String title) {
        SongHandler handler = getHandler(platform);
        return handler.downloadSongByTitle(title);
    }

}
