package com.dimetro.discordbot.musicservice.song.service.download;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.dto.request.download.*;
import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.handlers.SongHandlerAdapter;
import com.dimetro.discordbot.musicservice.song.dto.response.download.DownloadResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Download;
import com.dimetro.discordbot.musicservice.song.entity.DownloadStatus;
import com.dimetro.discordbot.musicservice.song.service.externalid.ExternalIdService;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;

@Service
public class SongDownloadService {
    
    private final DownloadService downloadService;
    private final SongService songService;
    private final ExecutorService downloadExecutorService;

    @Autowired
    public SongDownloadService(DownloadService downloadService, SongHandlerAdapter platformAdapter, 
                               SongService songService, ExternalIdService externalIdService,
                               @Value("${song.max-download-threads}")int maxThreads) {
        this.downloadService = downloadService;
        this.songService = songService;
        this.downloadExecutorService = Executors.newFixedThreadPool(maxThreads);
    }

    private void downloadSongThread(Song song, Download download) {

        try {
            songService.downloadSongAudioFile(song);
            download.setFinishTime(LocalDateTime.now());
            download.setStatus(DownloadStatus.DONE);
        } catch (Exception e) {
            download.setStatus(DownloadStatus.FAILED);
        } finally {
            downloadService.saveDownload(download);
        }

    }

    private DownloadResponseDTO downloadSong(Song song) {

        Optional<Download> dbDownload = downloadService.getActiveDownloadForSongSafe(song);
        
        if (dbDownload.isPresent()) {
            return DownloadResponseDTO.build(dbDownload.get());
        }

        Download download = new Download();

        if (song.getAudioFilePath() != null) {
            download.setStatus(DownloadStatus.DONE);
            download.setStartTime(LocalDateTime.now());
            download.setSong(song);
            return DownloadResponseDTO.build(downloadService.saveDownload(download));
        }

        download.setStatus(DownloadStatus.DOWNLOADING);
        download.setStartTime(LocalDateTime.now());
        download.setSong(song);

        Download savedDownload = downloadService.saveDownload(download);
        downloadExecutorService.submit(() -> downloadSongThread(song, savedDownload));

        return DownloadResponseDTO.build(download);
    }

    public DownloadResponseDTO downloadSongById(DownloadAudioByIdRequestDTO dto) {

        if (dto.getSongId() != null) {
            return downloadSongById(dto.getSongId());
        }

        return downloadSongByExternalId(dto.getPlatform(), dto.getExternalId());
    }

    private DownloadResponseDTO downloadSongById(UUID songId) {

        Song song = songService.getSongByIdOrThrow(songId);

        return downloadSong(song);
    }

    private DownloadResponseDTO downloadSongByExternalId(SongPlatform platform, String id) {
        
        Song song = songService.searchSongByPlatformAndExternalId(platform, id);

        return downloadSong(song);
    }

    public DownloadResponseDTO getDownload(UUID id) {

        Download download = downloadService.getDownloadByIdOrThrow(id);
        
        return DownloadResponseDTO.build(download);
    }

    public DownloadResponseDTO downloadSongByTitle(DownloadAudioByTitleRequestDTO dto) {

        Song song = songService.searchSongByPlatformAndTitle(dto.getPlatform(), dto.getTitle());

        return downloadSong(song);
    }

    public List<DownloadResponseDTO> downloadSongsByPlaylist(DownloadAudioByPlaylistIdDTO dto) {

        List<Song> songs = songService.searchSongsByExternalPlaylistId(dto.getPlatform(), dto.getPlaylistId());

        List<DownloadResponseDTO> dtos = new ArrayList<>();

        for (Song song : songs) {
            dtos.add(downloadSong(song));
        }

        return dtos;
    }

}
