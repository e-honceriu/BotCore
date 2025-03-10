package com.dimetro.discordbot.musicservice.handler.searcher.spotify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.searcher.SpotifySearcherConfig;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.SongSearcher;

@Component
public class SpotifySearcher extends SongSearcher {
    
    @Autowired
    public SpotifySearcher(SpotifySearcherConfig config, SpotifyDataConverter dataConverter, SpotifyApiWrapper apiWrapper) {
        super(config, dataConverter, apiWrapper);
    }

    public List<SongSearch> searchSongsByAlbumId(String albumId) {

        SpotifyApiWrapper spotifyApiWrapper = (SpotifyApiWrapper)apiWrapper;

        return converter.convert(spotifyApiWrapper.searchSongsByAlbumId(albumId));
    }

}
