package com.dimetro.discordbot.musicservice.handler.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.downloader.spotify.SpotifyDownloader;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.spotify.SpotifySearcher;

@Component
public class SpotifyHandler extends SongHandler {
    
    @Autowired
    public SpotifyHandler(SpotifySearcher songSearcher, SpotifyDownloader songDownloader) {
        super(songDownloader, songSearcher);
    }
    
    public List<SongSearch> searchSongsByAlbumId(String albumId) {
        SpotifySearcher spotifySongSearcher = (SpotifySearcher)songSearcher;
        return spotifySongSearcher.searchSongsByAlbumId(albumId);
    }

}
