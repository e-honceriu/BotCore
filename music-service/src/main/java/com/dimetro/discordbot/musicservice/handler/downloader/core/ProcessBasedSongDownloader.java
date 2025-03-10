package com.dimetro.discordbot.musicservice.handler.downloader.core;

import com.dimetro.discordbot.musicservice.handler.config.downloader.DownloaderConfig;
import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.DownloadExecutor;

public abstract class ProcessBasedSongDownloader extends SongDownloader {

    protected final DownloadExecutor downloadExecutor;

    public ProcessBasedSongDownloader(DownloaderConfig config, FileService fileService, DownloadExecutor downloadExecutor) {
        super(config, fileService);
        this.downloadExecutor = downloadExecutor;
    }

}
