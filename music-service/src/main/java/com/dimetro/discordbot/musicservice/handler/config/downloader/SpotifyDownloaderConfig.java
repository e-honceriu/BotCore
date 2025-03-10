package com.dimetro.discordbot.musicservice.handler.config.downloader;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifyDownloaderConfig extends DownloaderConfig {

    @Value("${spotify.temp-save-path}")
    private String tempSavePath;

    @Autowired
    public SpotifyDownloaderConfig(@Value("${spotify.save-path}") String savePath) {
        super(savePath);
    }

    public String getTempSavePath() {
        return tempSavePath;
    }

    public String generateTempSavePath(String id) {
        Path tempPath = Paths.get(tempSavePath); 
        Path fullPath = tempPath.resolve(id);
        return fullPath.toString();
    }

}
