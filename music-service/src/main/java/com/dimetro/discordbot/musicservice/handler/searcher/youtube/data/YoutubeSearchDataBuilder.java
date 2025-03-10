package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception.InvalidAPIResponseException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class YoutubeSearchDataBuilder {
    
    private static final Gson gson = new Gson();

    public static SearchData build(JsonObject item) {
        
        if (!item.has("kind")) {
            throw new InvalidAPIResponseException("kind");
        }

        Kind kind = Kind.fromString(
                                    item.getAsJsonObject()
                                        .get("kind")
                                        .getAsString()
                                );
        try {
            switch (kind) {
                case VIDEO -> {
                    return gson.fromJson(item, YoutubeVideoData.class);
                }
                case VIDEO_LIST_RESPONSE -> {
                    return gson.fromJson(item, YoutubeVideoListData.class);
                }
                case PLAYLIST -> {
                    return gson.fromJson(item, YoutubePlaylistData.class);
                }
                case PLAYLIST_ITEM -> {
                    return gson.fromJson(item, YoutubePlaylistData.class);
                }
                case SEARCH_LIST_RESPONSE -> {
                    return gson.fromJson(item, YoutubeSearchListData.class);
                }
                case SEARCH_RESULT -> {
                    return gson.fromJson(item, YoutubeSearchItemData.class);
                }
            }
        } catch (JsonSyntaxException e) {
            throw new InvalidAPIResponseException(e);
        }

        throw new InvalidAPIResponseException("kind");
    }

}
