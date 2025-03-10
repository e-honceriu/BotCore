package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.List;
import java.util.Optional;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubeSearchListData extends SearchData {

    private Kind kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private String regionCode;
    private PageInfo pageInfo;
    private List<YoutubeSearchItemData> items;

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

    public String getRegionCode() {
        return regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<YoutubeSearchItemData> getItems() {
        return items;
    }

    public static class PageInfo {

        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() { 
            return totalResults; 
        }

        public int getResultsPerPage() { 
            return resultsPerPage; 
        }

    }

}
