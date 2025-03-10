package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception.UnknownKindException;

public enum Kind {
    
    VIDEO("youtube#video"),
    VIDEO_LIST_RESPONSE("youtube#videoListResponse"),
    PLAYLIST("youtube#playlistItemListResponse"),
    PLAYLIST_ITEM("youtube#playlistItem"),
    SEARCH_RESULT("youtube#searchResult"),
    SEARCH_LIST_RESPONSE("youtube#searchListResponse");

    private final String value;

    Kind(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Kind fromString(String text) {
        for (Kind kind : Kind.values()) {
            if (kind.value.equalsIgnoreCase(text)) {
                return kind;
            }
        }
       throw new UnknownKindException(text);
    }

}
