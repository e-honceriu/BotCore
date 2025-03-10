package com.dimetro.discordbot.musicservice.song.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Download;

public interface DownloadRepository extends JpaRepository<Download, UUID> {
    
    List<Download> findAllBySong(Song song);

}
