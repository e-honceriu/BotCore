package com.dimetro.discordbot.musicservice.playlist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.musicservice.playlist.entity.PlaylistSong;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, UUID> {
    
}
