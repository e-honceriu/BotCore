package com.dimetro.discordbot.musicservice.handler.downloader.spotify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.downloader.SpotifyDownloaderConfig;
import com.dimetro.discordbot.musicservice.handler.downloader.core.FileService;
import com.dimetro.discordbot.musicservice.handler.downloader.core.ProcessBasedSongDownloader;
import com.dimetro.discordbot.musicservice.handler.downloader.spotify.exception.*;

@Component
public class SpotifyDownloader extends ProcessBasedSongDownloader {
    

    @Autowired
    public SpotifyDownloader(SpotifyDownloaderConfig config, FileService fileService, SpotifyDownloadExecutor downloadExecutor) {
        super(config, fileService, downloadExecutor);
    }

    private String moveSong(String id) {

        SpotifyDownloaderConfig spotifyConfig = (SpotifyDownloaderConfig) config;

        File tempDir = fileService.createDirectory(spotifyConfig.generateTempSavePath(id));

        if (!tempDir.exists()) {
            throw new NoTempFileFoundException(tempDir);
        }

        File[] tempDirFiles = tempDir.listFiles();

        if (tempDirFiles == null || tempDirFiles.length != 1) {
            throw new InvalidTempDirException(tempDir);
        }

        File finalFile = new File(spotifyConfig.generateSavePath(id));

        try {
            Files.move(tempDirFiles[0].toPath(), finalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new TempFileMoveException(tempDirFiles[0], finalFile);
        }

        tempDir.delete();
        return finalFile.getAbsolutePath();
    }

    @Override
    public String download(String id) {

        SpotifyDownloaderConfig spotifyConfig = (SpotifyDownloaderConfig) config;
        SpotifyDownloadExecutor spotifyExecutor = (SpotifyDownloadExecutor) downloadExecutor;

        fileService.createDirectory(config.getSavePath());
        File tempDir = fileService.createDirectory(spotifyConfig.generateTempSavePath(id));

        spotifyExecutor.execute(id, tempDir.getAbsolutePath());

        return moveSong(id);
    } 

}
