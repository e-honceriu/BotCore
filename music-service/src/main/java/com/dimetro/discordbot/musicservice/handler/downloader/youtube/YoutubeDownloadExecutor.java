package com.dimetro.discordbot.musicservice.handler.downloader.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.downloader.YoutubeDownloaderConfig;
import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.DownloadExecutor;
import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception.InvalidParamException;

@Component
public class YoutubeDownloadExecutor extends DownloadExecutor {

    private final String QUERY = "https://www.youtube.com/watch?v=";

    @Autowired
    public YoutubeDownloadExecutor(YoutubeDownloaderConfig config) {
        super(config.getMaxDownloadTimeSeconds());
    }
    
    @Override
    protected ProcessBuilder createProcessBuilder(int downloadId) {

        String savePath = paramManager.getParam(downloadId, "savePath");
        String songId = paramManager.getParam(downloadId, "songId");

        String queryString = QUERY + songId;

        return new ProcessBuilder(
                        "yt-dlp", "-x", "--audio-format", "mp3", 
                        "-o", savePath, queryString
                    );
    }

    public void execute(String songId, String savePath) {

        if (songId == null) {
            throw new InvalidParamException("songId");
        }

        if (savePath == null) {
            throw new InvalidParamException("savePath");
        }

        int downloadId = paramManager.initDownload();

        paramManager.setParam(downloadId, "songId", songId);
        paramManager.setParam(downloadId, "savePath", savePath);

        executeDownload(downloadId);
    }
    
}
