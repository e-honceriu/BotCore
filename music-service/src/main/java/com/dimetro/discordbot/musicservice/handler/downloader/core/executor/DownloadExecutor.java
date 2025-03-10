package com.dimetro.discordbot.musicservice.handler.downloader.core.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimetro.discordbot.musicservice.handler.downloader.core.executor.exception.DownloadException;

public abstract class DownloadExecutor {
    
    private final int maxWaitingTimeSeconds;
    protected final DownloadParamManager paramManager;
    private static final Logger logger = LoggerFactory.getLogger(DownloadExecutor.class);

    public DownloadExecutor(int maxWaitingTimeSeconds) {
        this.maxWaitingTimeSeconds = maxWaitingTimeSeconds;
        paramManager = new DownloadParamManager();
    }

    protected abstract ProcessBuilder createProcessBuilder(int downloadId);

    protected void executeDownload(int downloadId) {
        try {

        logger.info("Starting download process for ID: " + downloadId);

        ProcessBuilder pb = createProcessBuilder(downloadId);
        logger.info("Command: " + String.join(" ", pb.command()));

        Process process = pb.start();
        logger.info("Process started... waiting for completion.");

        boolean finished = process.waitFor(maxWaitingTimeSeconds, TimeUnit.SECONDS);

        if (!finished) {
            logger.info("Download timed out! Cleaning up...");
            paramManager.cleanUpDownload(downloadId);
            throw new DownloadException("Download timed out!");
        }

        int exitCode = process.exitValue();
        logger.info("Process finished with exit code: " + exitCode);

        if (exitCode == 0) {
            logger.info("Download successful! Cleaning up...");
            paramManager.cleanUpDownload(downloadId);
            return;
        }

        logger.info("Download failed! Reading error output...");
        StringBuilder err = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                err.append(line).append("\n");
            }
        }

        logger.info("Error Output:\n" + err.toString());
        paramManager.cleanUpDownload(downloadId);
        throw new DownloadException(err.toString());

    } catch (IOException | InterruptedException e) {
        logger.info("Exception occurred: " + e.getMessage());
        e.printStackTrace();
        throw new DownloadException(e);
    }
    }

}
