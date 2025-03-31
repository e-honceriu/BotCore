package com.dimetro.discordbot.musicservice.handler.searcher.youtube;

import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.searcher.core.DataConverter;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.data.*;

@Component
public class YoutubeDataConverter extends DataConverter {

    @Override
    protected void setupConverterMap() {
        converterMap.put(YoutubeVideoData.class, new YoutubeVideoDataConverter());
        converterMap.put(YoutubeSearchItemData.class, new YoutubeSearchItemDataConverter());
        converterMap.put(YoutubePlaylistItemData.class, new YoutubePlaylistItemDataConverter());
    }

}
