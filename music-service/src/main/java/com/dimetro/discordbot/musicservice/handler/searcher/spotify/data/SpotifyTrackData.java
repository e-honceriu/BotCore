package com.dimetro.discordbot.musicservice.handler.searcher.spotify.data;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class SpotifyTrackData extends SearchData {
    
    private final Track track;

    public SpotifyTrackData(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

}
