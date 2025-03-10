package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.WrongSearchDataTypeException;

public class YoutubePlaylistItemDataConverter implements SearchDataConverter {
    
    @Override
    public SongSearch convert(SearchData data) {

        if (data == null) {
            return null;
        }

        if (!(data instanceof YoutubePlaylistItemData)) {
            throw new WrongSearchDataTypeException(YoutubePlaylistItemData.class, data);
        }

        YoutubePlaylistItemData ytbData = (YoutubePlaylistItemData) data;

        String title = ytbData.getSnippet().getTitle();

        if (title.equalsIgnoreCase("deleted video") || title.equalsIgnoreCase("private video")) {
            return null;
        }

        return new SongSearch(
            ytbData.getSnippet().getResourceId().getVideoId(),
            SongPlatform.YOUTUBE,
            ytbData.getSnippet().getTitle(),
            ytbData.getSnippet().getThumbnails().get("default").getUrl()
        );
    }
    
}
