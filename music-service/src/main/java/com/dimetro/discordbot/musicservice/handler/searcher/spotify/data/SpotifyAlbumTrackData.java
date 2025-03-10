package com.dimetro.discordbot.musicservice.handler.searcher.spotify.data;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

public class SpotifyAlbumTrackData extends SearchData {
    
    private final TrackSimplified track;
    private final String thumbnailUrl;

    public SpotifyAlbumTrackData(TrackSimplified track, String thumbnailUrl) {
        this.track = track;
        this.thumbnailUrl = thumbnailUrl;
    }

    public TrackSimplified getTrack() {
        return track;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

}
