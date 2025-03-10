package com.dimetro.discordbot.musicservice.song.entity;

public enum DownloadStatus {
    
    DOWNLOADING("DOWNLOADING"),
    FAILED("FAILED"),
    DONE("DONE");

    private final String status;

    DownloadStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
