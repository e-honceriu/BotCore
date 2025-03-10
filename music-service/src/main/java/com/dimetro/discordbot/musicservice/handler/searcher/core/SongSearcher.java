package com.dimetro.discordbot.musicservice.handler.searcher.core;

import java.util.List;

import com.dimetro.discordbot.musicservice.handler.config.searcher.SearcherConfig;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.SongNotFoundException;

public class SongSearcher {
    
    protected final SearcherConfig config;
    protected final DataConverter converter;
    protected final SearchApiWrapper apiWrapper;

    public SongSearcher(SearcherConfig config, DataConverter converter, SearchApiWrapper apiWrapper) {
        this.config = config;
        this.converter = converter;
        this.apiWrapper = apiWrapper;
    }

    public SongSearch searchSongById(String id) {

        SearchData data = apiWrapper.searchSongById(id);
        
        SongSearch search = converter.convert(data);

        if (search == null) {
            throw new SongNotFoundException(id);
        }
        
        return search;
    }
    
    public SongSearch searchSongByTitle(String title) {

        SearchData data = apiWrapper.searchSongByTitle(title);
        
        SongSearch search = converter.convert(data);

        if (search == null) {
            throw new SongNotFoundException(title);
        }
        
        return search;
    }

    public List<SongSearch> searchSongsByPlaylistId(String playlistId) {

        List<SearchData> data = apiWrapper.searchSongsByPlaylistId(playlistId);

        return converter.convert(data);
    }

}
