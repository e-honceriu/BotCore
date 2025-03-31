package com.dimetro.discordbot.musicservice.handler.downloader.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.downloader.SpotifyDownloaderConfig;
import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.DownloadExecutor;
import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception.InvalidParamException;

@Component
public class SpotifyDownloadExecutor extends DownloadExecutor {

    private final String QUERY = "https://open.spotify.com/track/";

    @Autowired
    public SpotifyDownloadExecutor(SpotifyDownloaderConfig config) {
        super(config.getMaxDownloadTimeSeconds());
    }
    
    @Override
    protected ProcessBuilder createProcessBuilder(int downloadId) {
        
        String tempDirPath = paramManager.getParam(downloadId, "tempDirPath");
        String songId = paramManager.getParam(downloadId, "songId");

        String queryString = QUERY + songId;

        return new ProcessBuilder(
                        "spotdl", queryString,
                        "--output", tempDirPath
                    );
    }

    public void execute(String songId, String tempDirPath) {

        if (songId == null) {
            throw new InvalidParamException("songId");
        }

        if (tempDirPath == null) {
            throw new InvalidParamException("tempDirPath");
        }

        int downloadId = paramManager.initDownload();

        paramManager.setParam(downloadId, "songId", songId);
        paramManager.setParam(downloadId, "tempDirPath", tempDirPath);

        executeDownload(downloadId);
    } 
    
}
