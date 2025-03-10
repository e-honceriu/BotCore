package com.dimetro.discordbot.musicservice.handler.searcher.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.searcher.YoutubeSearcherConfig;
import com.dimetro.discordbot.musicservice.handler.searcher.core.SongSearcher;

@Component
public class YoutubeSongSearcher extends SongSearcher {

    @Autowired
    public YoutubeSongSearcher(YoutubeSearcherConfig config, YoutubeDataConverter dataConverter, YoutubeApiWrapper apiWrapper) {
        super(config, dataConverter, apiWrapper);
    }

}
