package com.dimetro.discordbot.musicservice.song.service.download;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Download;
import com.dimetro.discordbot.musicservice.song.entity.DownloadStatus;
import com.dimetro.discordbot.musicservice.song.repository.DownloadRepository;
import com.dimetro.discordbot.musicservice.song.service.download.exception.*;

import jakarta.transaction.Transactional;

@Service
public class DownloadService {
    
    private final DownloadRepository repository;

    @Autowired
    public DownloadService(DownloadRepository repository) {
        this.repository = repository;
    }

    public Download getDownloadByIdOrThrow(UUID id) {
        
        Optional<Download> dbDownload = repository.findById(id);

        if (dbDownload.isPresent()) {
            return dbDownload.get();
        }

        throw new DownloadNotFoundException(id);
    }

    @Transactional
    public Download getActiveDownloadForSongOrThrow(Song song) {

        List<Download> downloads = repository.findAllBySong(song);

        if (downloads.isEmpty()) {
            throw new DownloadNotFoundException(song);
        }

        List<Download> activeDownloads = downloads.stream()
                                            .filter(download -> download.getStatus() == DownloadStatus.DOWNLOADING)
                                            .collect(Collectors.toList());
        
        if (activeDownloads.isEmpty()) {
            throw new DownloadNotFoundException(song);
        }

        if (activeDownloads.size() != 1) {
            throw new MultipleActiveDownloadsException(song);
        }

        return activeDownloads.get(0);
    }

    @Transactional
    public Optional<Download> getActiveDownloadForSongSafe(Song song) {
        try {
            return Optional.of(getActiveDownloadForSongOrThrow(song));
        } catch (DownloadServiceException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Download saveDownload(Download download) {
        return repository.save(download);
    }
 
}
