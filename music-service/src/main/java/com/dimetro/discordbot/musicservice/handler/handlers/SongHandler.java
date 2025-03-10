package com.dimetro.discordbot.musicservice.handler.handlers;

import java.util.List;

import com.dimetro.discordbot.musicservice.handler.downloader.core.SongDownloader;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.searcher.core.SongSearcher;

public abstract class SongHandler {
    
    protected SongDownloader songDownloader;
    protected SongSearcher songSearcher;

    public SongHandler(SongDownloader songDownloader, SongSearcher songSearcher) {
        this.songDownloader = songDownloader;
        this.songSearcher = songSearcher;
    }

    public SongSearch searchSongById(String id) {
        return songSearcher.searchSongById(id);
    }

    public SongSearch searchSongByTitle(String title) {
        return songSearcher.searchSongByTitle(title);
    }

    public List<SongSearch> searchSongsByPlaylistId(String playlistId) {
        return songSearcher.searchSongsByPlaylistId(playlistId);
    }

    public String downloadSongById(String id) {
        return songDownloader.downloadSong(id);
    }

    public String downloadSongByTitle(String title) {
        SongSearch search = searchSongByTitle(title);
        return songDownloader.downloadSong(search.getId());
    }

}
