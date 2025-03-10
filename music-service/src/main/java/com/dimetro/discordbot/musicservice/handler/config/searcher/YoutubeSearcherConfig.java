package com.dimetro.discordbot.musicservice.handler.config.searcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YoutubeSearcherConfig extends SearcherConfig {

    @Value("${youtube.api-key}")
    private String apiKey;

    @Value("${youtube.max_playlist_search}")
    private int MAX_PLAYLIST_SEARCH;

    public String getAPIKey() {
        return apiKey;
    }

    public int getMaxPlaylistResults() {
        return MAX_PLAYLIST_SEARCH;
    }
    
}
