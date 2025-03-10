package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import java.util.Map;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;

public class YoutubePlaylistItemData extends SearchData {

    private Kind kind;
    private String etag;
    private String id;
    private Snippet snippet;

    public Kind getKind() { 
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
        private String playlistId;
        private int position;
        private ResourceId resourceId;
        private String videoOwnerChannelTitle;
        private String videoOwnerChannelId;

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

        public String getPlaylistId() { 
            return playlistId; 
        }

        public int getPosition() { 
            return position; 
        }

        public ResourceId getResourceId() { 
            return resourceId; 
        }

        public String getVideoOwnerChannelTitle() { 
            return videoOwnerChannelTitle; 
        }

        public String getVideoOwnerChannelId() { 
            return videoOwnerChannelId; 
        }

    }

    public static class ResourceId {

        private String kind;
        private String videoId;

        public String getKind() { 
            return kind;
        }

        public String getVideoId() { 
            return videoId; 
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
