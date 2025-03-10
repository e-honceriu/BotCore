package com.dimetro.discordbot.musicservice.handler.searcher.core.data;

import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;

public interface SearchDataConverter {
    
    SongSearch convert(SearchData data);

}
