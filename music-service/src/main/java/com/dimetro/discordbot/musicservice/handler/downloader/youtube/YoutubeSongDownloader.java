package com.dimetro.discordbot.musicservice.handler.downloader.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.downloader.YoutubeDownloaderConfig;
import com.dimetro.discordbot.musicservice.handler.downloader.core.FileService;
import com.dimetro.discordbot.musicservice.handler.downloader.core.ProcessBasedSongDownloader;


@Component
public class YoutubeSongDownloader extends ProcessBasedSongDownloader {
    
    @Autowired
    public YoutubeSongDownloader(YoutubeDownloaderConfig config, FileService fileService, YoutubeDownloadExecutor downloadExecutor) {
        super(config, fileService, downloadExecutor);
    }

    @Override
    public String download(String id) {

        fileService.createDirectory(config.getSavePath());

        YoutubeDownloaderConfig ytbConfig = (YoutubeDownloaderConfig) config;
        YoutubeDownloadExecutor ytbExecutor = (YoutubeDownloadExecutor) downloadExecutor;
            
        String savePath = ytbConfig.generateSavePath(id);
        ytbExecutor.execute(id, savePath);

        return savePath;
    }

}
