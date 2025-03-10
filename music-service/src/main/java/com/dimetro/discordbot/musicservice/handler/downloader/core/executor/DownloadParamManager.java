package com.dimetro.discordbot.musicservice.handler.downloader.core.executor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadParamManager {
    
    private final ConcurrentHashMap<Integer, ConcurrentHashMap<String, String>> params = new ConcurrentHashMap<>();
    
    private final AtomicInteger downloadIdCounter = new AtomicInteger(0);

    public int initDownload() {
        int downloadId = downloadIdCounter.getAndIncrement(); 
        params.put(downloadId, new ConcurrentHashMap<>());
        return downloadId;
    }

    public void setParam(int downloadId, String key, String value) {
        if (params.containsKey(downloadId)) {
            params.get(downloadId).put(key, value);
        } else {
            throw new IllegalArgumentException("Download ID does not exist: " + downloadId);
        }
    }

    public String getParam(int downloadId, String key) {
        if (params.containsKey(downloadId)) {
            return params.get(downloadId).get(key);
        } else {
            throw new IllegalArgumentException("Download ID does not exist: " + downloadId);
        }
    }

    public void cleanUpDownload(int downloadId) {
        params.remove(downloadId);
    }
    
}
