package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchData;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.SearchDataConverter;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.WrongSearchDataTypeException;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception.LiveBroadcastFoundException;


public class YoutubeVideoDataConverter implements SearchDataConverter {

    @Override
    public SongSearch convert(SearchData data) {

        if (data == null) {
            return null;
        }

        if (!(data instanceof YoutubeVideoData)) {
            throw new WrongSearchDataTypeException(YoutubeVideoData.class, data);
        }

        YoutubeVideoData ytbData = (YoutubeVideoData) data;

        if (ytbData.getSnippet().getLiveBroadcastContent() != LiveBroadcastStatus.NONE) {
            throw new LiveBroadcastFoundException();
        }

        return new SongSearch(
            ytbData.getId(),
            SongPlatform.YOUTUBE,
            ytbData.getSnippet().getTitle(),
            ytbData.getSnippet().getThumbnails().get("default").getUrl()
        );
        
    }
    
}