package com.dimetro.discordbot.musicservice.handler.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.downloader.youtube.YoutubeSongDownloader;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.YoutubeSongSearcher;

@Component
public class YoutubeHandler extends SongHandler {

    @Autowired
    public YoutubeHandler(YoutubeSongDownloader songDownloader, YoutubeSongSearcher songSearcher) {
        super(songDownloader, songSearcher);
    }

}
