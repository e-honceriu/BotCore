package com.dimetro.discordbot.musicservice.handler.searcher.spotify;

import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.searcher.core.DataConverter;
import com.dimetro.discordbot.musicservice.handler.searcher.spotify.data.*;

@Component
public class SpotifyDataConverter extends DataConverter {

    @Override
    protected void setupConverterMap() {
        converterMap.put(SpotifyTrackData.class, new SpotifyTrackDataConverter());
        converterMap.put(SpotifyAlbumTrackData.class, new SpotifyAlbumTrackDataConverter());
    }

}
