package com.dimetro.discordbot.musicservice.playlist.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    
    Optional<Playlist> findByTitleAndBotIdAndGuildDiscordId(String title, UUID botId, String guildDiscordId);
    List<Playlist> findByGuildDiscordIdAndBotId(String guildDiscordId, UUID botId);

}
