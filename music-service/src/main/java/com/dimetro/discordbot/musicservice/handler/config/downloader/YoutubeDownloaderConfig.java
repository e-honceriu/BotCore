package com.dimetro.discordbot.musicservice.handler.config.downloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YoutubeDownloaderConfig extends DownloaderConfig {
   
    @Autowired
    public YoutubeDownloaderConfig(@Value("${youtube.save-path}") String savePath) {
        super(savePath);
    }

}
