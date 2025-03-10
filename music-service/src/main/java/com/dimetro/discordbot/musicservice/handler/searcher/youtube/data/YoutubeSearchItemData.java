package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.Map;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubeSearchItemData extends SearchData {

    private Kind kind;
    private String etag;
    private VideoId id;
    private Snippet snippet;

    public Kind getKind() { 
        return kind; 
    }

    public String getEtag() { 
        return etag; 
    }

    public VideoId getId() { 
        return id; 
    }

    public Snippet getSnippet() { 
        return snippet; 
    }

    public static class VideoId {

        private String kind;
        private String videoId;

        public String getKind() { 
            return kind; 
        }

        public String getVideoId() { 
            return videoId; 
        }
        
    }

    public static class Snippet {

        private String publishedAt;
        private String channelId;
        private String title;
        private String description;
        private Map<String, Thumbnail> thumbnails;
        private String channelTitle;
        private LiveBroadcastStatus liveBroadcastContent;
        private String publishTime;

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

        public LiveBroadcastStatus getLiveBroadcastContent() { 
            return liveBroadcastContent; 
        }

        public String getPublishTime() { 
            return publishTime; 
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

}
