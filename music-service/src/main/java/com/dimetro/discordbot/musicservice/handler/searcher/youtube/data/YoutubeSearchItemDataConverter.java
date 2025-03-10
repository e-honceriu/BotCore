package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.WrongSearchDataTypeException;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception.LiveBroadcastFoundException;

public class YoutubeSearchItemDataConverter implements SearchDataConverter {

    @Override
    public SongSearch convert(SearchData data) {

        if (data == null) {
            return null;
        }

        if (!(data instanceof YoutubeSearchItemData)) {
            throw new WrongSearchDataTypeException(YoutubeSearchItemData.class, data);
        }

        YoutubeSearchItemData ytbData = (YoutubeSearchItemData) data;
        
        if (ytbData.getSnippet().getLiveBroadcastContent() != LiveBroadcastStatus.NONE) {
            throw new LiveBroadcastFoundException();
        }

        return new SongSearch(
            ytbData.getId().getVideoId(),
            SongPlatform.YOUTUBE,
            ytbData.getSnippet().getTitle(),
            ytbData.getSnippet().getThumbnails().get("default").getUrl()
        );
    }
    
}
