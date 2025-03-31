package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.List;
import java.util.Map;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubeVideoData extends SearchData {
    
    private String kind;
    private String etag;
    private String id;
    private Snippet snippet;

    public String getKind() {
        return kind; 
    }

    public String getEtag() { 
        return etag; 
    }

    public String getId() { 
        return id; 
    }

    public Snippet getSnippet() { 
        return snippet; 
    }

    public static class Snippet {

        private String publishedAt;
        private String channelId;
        private String title;
        private String description;
        private Map<String, Thumbnail> thumbnails;
        private String channelTitle;
        private List<String> tags;
        private String categoryId;
        private LiveBroadcastStatus liveBroadcastContent;
        private Localized localized;

        public String getPublishedAt() { 
            return publishedAt; 
        }

        public String getChannelId() { 
            return channelId; 
        }
        
        public String getTitle() { 
            return title; 
        }

        public String getDescription() { 
            return description; 
        }

        public Map<String, Thumbnail> getThumbnails() { 
            return thumbnails; 
        }

        public String getChannelTitle() { 
            return channelTitle; 
        }

        public List<String> getTags() { 
            return tags; 
        }

        public String getCategoryId() { 
            return categoryId; 
        }

        public LiveBroadcastStatus getLiveBroadcastContent() { 
            return liveBroadcastContent; 
        }

        public Localized getLocalized() { 
            return localized; 
        }

    }

    public static class Thumbnail {
        
        private String url;
        private int width;
        private int height;

        public String getUrl() { 
            return url; 
        }

        public int getWidth() { 
            return width; 
        }

        public int getHeight() { 
            return height; 
        }

    }

    public static class Localized {

        private String title;
        private String description;

        public String getTitle() { 
            return title; 
        }

        public String getDescription() { 
            return description; 
        }

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
