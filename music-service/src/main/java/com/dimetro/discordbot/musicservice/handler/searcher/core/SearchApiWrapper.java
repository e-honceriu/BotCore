package com.dimetro.discordbot.musicservice.handler.searcher.core;

import java.util.List;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;

public abstract class SearchApiWrapper {
    
    public abstract SearchData searchSongById(String id);
    public abstract SearchData searchSongByTitle(String title);
    public abstract List<SearchData> searchSongsByPlaylistId(String playlistId);
    
}
