package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.List;
import java.util.Optional;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubePlaylistData extends SearchData {
    
    private Kind kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private List<YoutubePlaylistItemData> items;

    public Kind getKind() { 
        return kind; 
    }

    public String getEtag() { 
        return etag; 
    }

    public Optional<String> getNextPageToken() { 
        return Optional.ofNullable(nextPageToken); 
    }

    public Optional<String> getPrevPageToken() { 
        return Optional.ofNullable(prevPageToken); 
    }

    public List<YoutubePlaylistItemData> getItems() { 
        return items; 
    }

}
