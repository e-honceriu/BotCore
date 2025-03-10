package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.List;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubeVideoListData extends SearchData {
    
    private Kind kind;
    private String etag;
    private List<YoutubeVideoData> items;
    private PageInfo pageInfo;

    public Kind getKind() { 
        return kind; 
    }

    public String getEtag() { 
        return etag; 
    }

    public List<YoutubeVideoData> getItems() { 
        return items; 
    }

    public PageInfo getPageInfo() { 
        return pageInfo; 
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
