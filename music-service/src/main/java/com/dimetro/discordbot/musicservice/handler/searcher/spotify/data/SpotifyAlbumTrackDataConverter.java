package com.dimetro.discordbot.musicservice.handler.searcher.spotify.data;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.WrongSearchDataTypeException;

import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

public class SpotifyAlbumTrackDataConverter implements SearchDataConverter {
    
    @Override
    public SongSearch convert(SearchData data) {

        if (data == null) {
            return null;
        }

        if (!(data instanceof SpotifyAlbumTrackData)) {
            throw new WrongSearchDataTypeException(SpotifyAlbumTrackData.class, data);
        }

        SpotifyAlbumTrackData spotifyData = (SpotifyAlbumTrackData) data;
        
        TrackSimplified track = spotifyData.getTrack();
        
        return new SongSearch(
            track.getId(),
            SongPlatform.SPOTIFY,
            track.getName(),
            spotifyData.getThumbnailUrl()
        );

    }

}
