package com.dimetro.discordbot.musicservice.handler.config.downloader;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;

public class DownloaderConfig {
    
    private final String savePath;
    
    @Value("${song.max-download-time-seconds}")
    private int maxDownloadTimeSeconds;

    public DownloaderConfig(String savePath) {
        this.savePath = savePath;
    }

    public String generateSavePath(String id) {
        return Paths.get(savePath, id + ".mp3").toString();
    }

    public String getSavePath() {
        return savePath;
    }

    public int getMaxDownloadTimeSeconds() {
        return maxDownloadTimeSeconds;
    }
    
}
