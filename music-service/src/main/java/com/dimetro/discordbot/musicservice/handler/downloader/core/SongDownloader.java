package com.dimetro.discordbot.musicservice.handler.downloader.core;

import com.dimetro.discordbot.musicservice.handler.config.downloader.DownloaderConfig;

public abstract class SongDownloader {
    
    protected final DownloaderConfig config;
    protected final FileService fileService;

    public SongDownloader(DownloaderConfig config, FileService fileService) {
        this.config = config;
        this.fileService = fileService;
    }

    protected abstract String download(String id);

    public String downloadSong(String id) {

        String savePath = config.generateSavePath(id);

        if (fileService.fileExists(savePath)) {
            return savePath;
        }
        
        return download(id);
    }
    
}
